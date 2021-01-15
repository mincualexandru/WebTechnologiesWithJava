package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.symptom.Symptom;
import com.rest.workshop.presentation.dtos.symptom.CreateSymptomDto;
import com.rest.workshop.presentation.dtos.symptom.SymptomDto;
import com.rest.workshop.presentation.dtos.symptom.UpdateSymptomDto;
import com.rest.workshop.presentation.dtos.user.UpdateEnabledDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public interface SymptomService {

    Symptom save(Symptom symptom);

    Symptom findById(Long symptomId);

    void delete(Symptom symptom);

    List<Symptom> findAll();

    Optional<Symptom> findByIdOptional(Long symptomId);

    SymptomDto create(ModelMapper modelMapper, CreateSymptomDto createSymptomDto);

    SymptomDto updateAvailability(ModelMapper modelMapper, Long reportId, UpdateEnabledDto action);

    boolean checkIfUserIsOwner(Long symptomId);

    List<SymptomDto> viewAllOpen(ModelMapper modelMapper);

    SymptomDto getById(ModelMapper modelMapper, Long symptomId);

    boolean checkOwnerOfTheReport(Long symptomId);

    SymptomDto update(ModelMapper modelMapper, Long symptomId, UpdateSymptomDto updateSymptomDto);

    void deleteById(Long symptomId);
}
