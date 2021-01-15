package com.rest.workshop.services.implementation.illness;

import com.rest.workshop.domain.entitties.illness.Illness;
import com.rest.workshop.domain.search_params.IllnessSearchParams;
import com.rest.workshop.presentation.dtos.illness.CreateIllnessDto;
import com.rest.workshop.presentation.dtos.illness.IllnessDto;
import com.rest.workshop.services.IllnessService;
import com.rest.workshop.services.SymptomService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.stream.Collectors;

@Service
@Transactional
public class IllnessServiceImpl implements IllnessService {

    private final IllnessRepository illnessRepository;
    private final SymptomService symptomService;

    public IllnessServiceImpl(IllnessRepository illnessRepository, SymptomService symptomService) {
        this.illnessRepository = illnessRepository;
        this.symptomService = symptomService;
    }

    @Override
    public void save(Illness illness) {
        illnessRepository.save(illness);
    }

    @Override
    public Illness findById(Long illnessId) {
        return illnessRepository.findById(illnessId).orElseThrow(NotFoundException::new);
    }

    @Override
    public IllnessDto create(ModelMapper modelMapper, CreateIllnessDto createIllnessDto) {
        Illness illness = modelMapper.map(createIllnessDto, Illness.class);
        illness.setSymptoms(
                createIllnessDto.symptomsIds
                        .stream()
                        .map(symptomService::findById).collect(Collectors.toList()));
        save(illness);

        return modelMapper.map(illness, IllnessDto.class);
    }

    @Override
    public IllnessDto update(ModelMapper modelMapper, Long illnessId, CreateIllnessDto createIllnessDto) {
        Illness illness = findById(illnessId);
        modelMapper.map(createIllnessDto, illness);
        illness.setSymptoms(
                createIllnessDto.symptomsIds
                        .stream()
                        .map(symptomService::findById).collect(Collectors.toList()));
        illness.setDiagnoses(createIllnessDto.diagnoses);
        illness.setRisks(createIllnessDto.risks);
        save(illness);

        return modelMapper.map(illness, IllnessDto.class);
    }

    @Override
    public void deleteById(Long illnessId) {
        illnessRepository.deleteById(illnessId);
    }

    @Override
    public IllnessDto getById(ModelMapper modelMapper, Long illnessId) {
        Illness illness = illnessRepository.findById(illnessId).orElseThrow(NotFoundException::new);
        return modelMapper.map(illness, IllnessDto.class);
    }

    @Override
    public Page<IllnessDto> findAll(ModelMapper modelMapper, Pageable page, IllnessSearchParams searchParams) {
        Page<Illness> illnesses = illnessRepository.findAll(page, searchParams);
        return illnesses.map(offer -> modelMapper.map(offer, IllnessDto.class));
    }
}
