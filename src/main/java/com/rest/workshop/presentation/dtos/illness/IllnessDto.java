package com.rest.workshop.presentation.dtos.illness;

import com.rest.workshop.domain.entitties.illness.Diagnosis;
import com.rest.workshop.domain.entitties.illness.Risk;
import com.rest.workshop.presentation.dtos.AuditEntityDto;
import com.rest.workshop.presentation.dtos.symptom.SymptomListingDto;

import java.util.ArrayList;
import java.util.List;

public class IllnessDto extends AuditEntityDto {
    public String content;
    public List<SymptomListingDto> symptoms = new ArrayList<>();
    public List<Risk> risks = new ArrayList<>();
    public List<Diagnosis> diagnoses = new ArrayList<>();
}
