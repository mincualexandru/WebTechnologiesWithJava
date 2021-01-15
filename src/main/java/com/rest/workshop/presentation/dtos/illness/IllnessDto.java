package com.rest.workshop.presentation.dtos.illness;

import com.rest.workshop.domain.entitties.illness.Diagnosis;
import com.rest.workshop.domain.entitties.illness.Risk;
import com.rest.workshop.presentation.dtos.symptom.SymptomDto;

import java.util.ArrayList;
import java.util.List;

public class IllnessDto {
    public String content;
    public List<SymptomDto> symptoms = new ArrayList<>();
    public List<Risk> risks = new ArrayList<>();
    public List<Diagnosis> diagnoses = new ArrayList<>();
}
