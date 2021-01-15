package com.rest.workshop.presentation.dtos.symptom.question;

import com.rest.workshop.presentation.dtos.symptom.answer.CreateAnswerDto;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CreateQuestionDto {
    @NotNull
    public String message;

    @NotNull
    public boolean required;

    @NotNull
    public Set<CreateAnswerDto> answers = new HashSet<>();
}
