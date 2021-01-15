package com.rest.workshop.presentation.dtos.symptom;

import com.rest.workshop.presentation.dtos.AuditEntityDto;
import com.rest.workshop.presentation.dtos.symptom.question.QuestionDto;

import java.util.HashSet;
import java.util.Set;

public class SymptomDto extends AuditEntityDto {
    public String title;
    public boolean enabled;
    public Set<QuestionDto> questions = new HashSet<>();
}
