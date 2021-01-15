package com.rest.workshop.presentation.dtos.symptom.evaluation;

import com.rest.workshop.presentation.dtos.symptom.respondent_answer.CreateRespondentAnswerDto;

import java.util.HashSet;
import java.util.Set;

public class CreateEvaluationDto {
    public Long symptomId;

    public Set<CreateRespondentAnswerDto> respondentAnswers = new HashSet<>();

}
