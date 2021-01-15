package com.rest.workshop.presentation.dtos.symptom;

import com.rest.workshop.presentation.dtos.symptom.question.UpdateQuestionDto;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UpdateSymptomDto {
    @NotNull
    public String title;

    @NotNull
    public boolean enabled;

    @NotNull
    public List<UpdateQuestionDto> questions = new ArrayList<>();
}
