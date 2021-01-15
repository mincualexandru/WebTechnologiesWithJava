package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.symptom.Evaluation;
import com.rest.workshop.presentation.dtos.symptom.evaluation.CreateEvaluationDto;
import com.rest.workshop.presentation.dtos.symptom.evaluation.EvaluationDto;

import java.util.List;
import java.util.Optional;

public interface EvaluationService {
    Evaluation save(Evaluation evaluation);

    Evaluation findById(Long evaluationId);

    void delete(Evaluation evaluation);

    List<Evaluation> findAll();

    List<Evaluation> findAllByRespondentId(Long respondentId);

    Optional<Evaluation> findByRespondentIdAndId(Long respondentId, Long symptomId);

    void checkSymptomCompletion(Long symptomId);

    Optional<Evaluation> findByRespondentIdAndSymptomId(Long respondentId, Long symptomId);

    void checkRequiredQuestions(Long symptomId, CreateEvaluationDto response);

    List<Evaluation> findAllBySymptomId(Long symptomId);

    List<EvaluationDto> getAll();

    EvaluationDto create(CreateEvaluationDto response);

    EvaluationDto getById(Long evaluationId);

    boolean checkEvaluationFromUser(Long evaluationId);

    void deleteById(Long evaluationId);
}
