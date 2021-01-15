package com.rest.workshop.services.implementation.symptom.evaluation;

import com.rest.workshop.domain.entitties.symptom.Question;
import com.rest.workshop.domain.entitties.symptom.Symptom;
import com.rest.workshop.domain.entitties.symptom.Evaluation;
import com.rest.workshop.domain.entitties.user.Role;
import com.rest.workshop.domain.entitties.user.User;
import com.rest.workshop.presentation.dtos.symptom.evaluation.CreateEvaluationDto;
import com.rest.workshop.presentation.dtos.symptom.evaluation.EvaluationDto;
import com.rest.workshop.services.*;
import com.rest.workshop.util.CustomizedException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final SymptomService symptomService;
    private final UserService userService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    public EvaluationServiceImpl(
            EvaluationRepository evaluationRepository,
            SymptomService symptomService,
            UserService userService,
            QuestionService questionService, ModelMapper modelMapper) {
        this.evaluationRepository = evaluationRepository;
        this.symptomService = symptomService;
        this.userService = userService;
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation findById(Long id) {
        return evaluationRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Evaluation evaluation) {
        evaluationRepository.delete(evaluation);
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    @Override
    public List<Evaluation> findAllByRespondentId(Long respondentId) {
        return evaluationRepository.findAllByRespondentId(respondentId);
    }

    @Override
    public Optional<Evaluation> findByRespondentIdAndId(Long respondentId, Long surveyId) {
        return evaluationRepository.findByRespondentIdAndId(respondentId, surveyId);
    }

    @Override
    public void checkSymptomCompletion(Long reportId) {
        Long respondentId = userService.getCurrentUser().getId();

        if(findByRespondentIdAndSymptomId(respondentId, reportId).isPresent() ||
                !symptomService.findById(reportId).getEnabled()) {
            throw new CustomizedException("Something went wrong!");
        }
    }

    @Override
    public Optional<Evaluation> findByRespondentIdAndSymptomId(Long respondentId, Long reportId) {
        return evaluationRepository.findByRespondentIdAndSymptomId(respondentId, reportId);
    }

    @Override
    public void checkRequiredQuestions(Long surveyId, CreateEvaluationDto response) {
        Symptom selectedSymptom = symptomService.findById(surveyId);
        List<Question> copiedQuestions = selectedSymptom.getQuestions();
        copiedQuestions.removeIf(question -> !question.getRequired());
        List<Question> answeredQuestions = new ArrayList<>();
        response.respondentAnswers.forEach(element -> {
            answeredQuestions.add(questionService.findById(element.questionId));
        });

        if(!answeredQuestions.containsAll(copiedQuestions)) {
            throw new CustomizedException("You did not answer all the required questions!");
        }
    }

    @Override
    public List<Evaluation> findAllBySymptomId(Long surveyId) {
        return evaluationRepository.findAllBySymptomId(surveyId);
    }

    @Override
    public List<EvaluationDto> getAll() {
        User connectedAccount = userService.getCurrentUser();
        return getResponses(connectedAccount)
                .stream()
                .map(offerAsset -> modelMapper.map(offerAsset, EvaluationDto.class))
                .collect(Collectors.toList());
    }

    private List<Evaluation> getResponses(User connectedAccount) {
        List<Evaluation> respons = new ArrayList<>();

        if(Role.ADMIN.equals(connectedAccount.getRole())) {
            connectedAccount.getSymptoms().forEach(element -> {
                if(!findAllBySymptomId(element.getId()).isEmpty()) {
                    respons.addAll(findAllBySymptomId(element.getId()));
                }
            });
        } else if(Role.USER.equals(connectedAccount.getRole())) {
            respons.addAll(findAllByRespondentId(connectedAccount.getId()));
        }

        return respons;
    }

    @Override
    public EvaluationDto create(CreateEvaluationDto response) {
        Long reportId = response.symptomId;

        checkSymptomCompletion(reportId);
        checkRequiredQuestions(reportId, response);

        Evaluation newEvaluation = modelMapper.map(response, Evaluation.class);
        newEvaluation.setRespondent(userService.getCurrentUser());
        newEvaluation.getRespondentAnswers().forEach(element -> {
            element.setEvaluation(newEvaluation);
        });

        return modelMapper.map(save(newEvaluation), EvaluationDto.class);
    }

    @Override
    public EvaluationDto getById(Long responseId) {
        Evaluation selectedEvaluation = findById(responseId);
        return modelMapper.map(selectedEvaluation, EvaluationDto.class);
    }

    @Override
    public boolean checkEvaluationFromUser(Long responseId) {
        return findByRespondentIdAndId(userService.getCurrentUser().getId(), responseId).isPresent();
    }

    @Override
    public void deleteById(Long symptomId) {
        evaluationRepository.deleteById(symptomId);
    }

}
