package com.rest.workshop.presentation.dtos.symptom;

import com.rest.workshop.presentation.dtos.symptom.question.CreateQuestionDto;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CreateSymptomDto {
    @NotNull
    public String title;

    @NotNull
    public boolean enabled;

    @NotNull
    public Set<CreateQuestionDto> questions = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<CreateQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<CreateQuestionDto> questions) {
        this.questions = questions;
    }
}
