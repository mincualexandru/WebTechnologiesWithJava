package com.rest.workshop.presentation.dtos.symptom.evaluation;

import com.rest.workshop.presentation.dtos.AuditEntityDto;
import com.rest.workshop.presentation.dtos.symptom.SymptomListingDto;
import com.rest.workshop.presentation.dtos.symptom.respondent_answer.RespondentAnswerDto;

import java.util.HashSet;
import java.util.Set;

public class EvaluationDto extends AuditEntityDto {
    public SymptomListingDto symptom;

    public Set<RespondentAnswerDto> respondentAnswers = new HashSet<>();
}
