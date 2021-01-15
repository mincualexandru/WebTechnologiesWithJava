package com.rest.workshop.presentation.dtos.symptom.question;

import com.rest.workshop.presentation.dtos.symptom.answer.AnswerDto;

import java.util.HashSet;
import java.util.Set;

public class QuestionDto extends QuestionListingDto {
    public Set<AnswerDto> answers = new HashSet<>();
}
