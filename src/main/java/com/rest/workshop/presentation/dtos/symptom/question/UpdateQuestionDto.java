package com.rest.workshop.presentation.dtos.symptom.question;

import com.rest.workshop.presentation.dtos.symptom.answer.UpdateAnswerDto;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class UpdateQuestionDto {

    @NotNull
    public Long id;

    public String message;

    @NotNull
    public boolean required;

    @NotNull
    public Set<UpdateAnswerDto> answers = new HashSet<>();
}
