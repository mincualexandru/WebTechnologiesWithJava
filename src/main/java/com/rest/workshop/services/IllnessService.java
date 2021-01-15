package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.illness.Illness;
import com.rest.workshop.domain.search_params.IllnessSearchParams;
import com.rest.workshop.presentation.dtos.illness.CreateIllnessDto;
import com.rest.workshop.presentation.dtos.illness.IllnessDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IllnessService {
    void save(Illness illness);

    Illness findById(Long questionId);

    IllnessDto create(ModelMapper modelMapper, CreateIllnessDto createIllnessDto);

    IllnessDto update(ModelMapper modelMapper, Long illnessId, CreateIllnessDto createIllnessDto);

    void deleteById(Long illnessId);

    IllnessDto getById(ModelMapper modelMapper, Long illnessId);

    Page<IllnessDto> findAll(ModelMapper modelMapper, Pageable page, IllnessSearchParams searchParams);
}
