package com.rest.workshop.presentation.dtos.illness;

import com.rest.workshop.domain.entitties.illness.Diagnosis;
import com.rest.workshop.domain.entitties.illness.Risk;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CreateIllnessDto {
    @NotEmpty
    public String content;

    public List<Long> symptomsIds = new ArrayList<>();

    @Size(min = 1)
    public List<Risk> risks = new ArrayList<>();

    @Size(min = 1)
    public List<Diagnosis> diagnoses = new ArrayList<>();
}
