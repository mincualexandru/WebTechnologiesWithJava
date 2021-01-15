package com.rest.workshop.services.implementation.symptom;

import com.rest.workshop.domain.entitties.symptom.Answer;
import com.rest.workshop.domain.entitties.symptom.Question;
import com.rest.workshop.domain.entitties.symptom.Symptom;
import com.rest.workshop.domain.entitties.user.Role;
import com.rest.workshop.domain.entitties.user.User;
import com.rest.workshop.presentation.dtos.symptom.CreateSymptomDto;
import com.rest.workshop.presentation.dtos.symptom.SymptomDto;
import com.rest.workshop.presentation.dtos.symptom.UpdateSymptomDto;
import com.rest.workshop.presentation.dtos.user.UpdateEnabledDto;
import com.rest.workshop.services.SymptomService;
import com.rest.workshop.services.UserService;
import com.rest.workshop.services.implementation.symptom.evaluation.EvaluationRepository;
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
public class SymptomServiceImpl implements SymptomService {

    private final SymptomRepository symptomRepository;
    private final EvaluationRepository evaluationRepository;
    private final UserService userService;

    public SymptomServiceImpl(SymptomRepository symptomRepository, EvaluationRepository evaluationRepository, UserService userService) {
        this.symptomRepository = symptomRepository;
        this.evaluationRepository = evaluationRepository;
        this.userService = userService;
    }

    @Override
    public Symptom save(Symptom symptom) {
        return symptomRepository.save(symptom);
    }

    @Override
    public Symptom findById(Long id) {
        return symptomRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Symptom symptom) {

    }

    @Override
    public List<Symptom> findAll() {
        return null;
    }

    @Override
    public Optional<Symptom> findByIdOptional(Long surveyId) {
        return Optional.empty();
    }

    @Override
    public SymptomDto create(ModelMapper modelMapper, CreateSymptomDto createSymptomDto) {
        User currentUser = userService.getCurrentUser();
        Symptom newSymptom = modelMapper.map(createSymptomDto, Symptom.class);

        newSymptom.setOwner(currentUser);
        newSymptom.getQuestions().forEach(question -> {
            question.setSymptom(newSymptom);
            question.getAnswers().forEach(answer -> answer.setQuestion(question));
        });

        currentUser.getSymptoms().add(newSymptom);

        userService.save(currentUser);

        return modelMapper.map(newSymptom, SymptomDto.class);
    }

    @Override
    public SymptomDto updateAvailability(ModelMapper modelMapper, Long reportId, UpdateEnabledDto action) {
        Symptom selectedSurvey = findById(reportId);
        modelMapper.map(action, selectedSurvey);
        return modelMapper.map(save(selectedSurvey), SymptomDto.class);
    }

    @Override
    public boolean checkIfUserIsOwner(Long reportId) {
        User connectedAccount = userService.getCurrentUser();
        Symptom selectedSymptom = findById(reportId);
        return connectedAccount.getSymptoms().contains(selectedSymptom);
    }

    @Override
    public List<SymptomDto> viewAllOpen(ModelMapper modelMapper) {
        List<Symptom> openSurveys = new ArrayList<>();
        List<Symptom> respondedSurveys = new ArrayList<>();
        User connectedAccount = userService.getCurrentUser();
        userService.findAllByRole(Role.ADMIN)
                .forEach(coordinator -> openSurveys.addAll(coordinator.getSymptoms()));
        evaluationRepository.findAllBySymptomId(connectedAccount.getId())
                .forEach(element -> respondedSurveys.add(element.getSymptom()));
        openSurveys.removeAll(respondedSurveys);

        return openSurveys
                .stream()
                .map(report -> modelMapper.map(report, SymptomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SymptomDto getById(ModelMapper modelMapper, Long surveyId) {
        return modelMapper.map(findById(surveyId), SymptomDto.class);
    }

    @Override
    public boolean checkOwnerOfTheReport(Long surveyId) {
        return findByIdOptional(surveyId).isPresent() ||
                findById(surveyId).getEnabled();
    }

    @Override
    public SymptomDto update(ModelMapper modelMapper, Long symptomId, UpdateSymptomDto updateSymptomDto) {
        Symptom selectedSymptom = findById(symptomId);
        modelMapper.map(updateSymptomDto, selectedSymptom);

        save(selectedSymptom);
        return modelMapper.map(selectedSymptom, SymptomDto.class);
    }

    @Override
    public void deleteById(Long symptomId) {
        symptomRepository.deleteById(symptomId);
    }
}
