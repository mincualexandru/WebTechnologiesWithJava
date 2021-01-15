package com.rest.workshop.config.mapper;

import org.modelmapper.ModelMapper;

public interface EntityDtoMappingRule extends MappingRule {

    @Override
    default void addMappings(ModelMapper modelMapper) {
        entityToDtoMapping(modelMapper);
        dtoToEntityMapping(modelMapper);
    }

    void entityToDtoMapping(ModelMapper modelMapper);

    void dtoToEntityMapping(ModelMapper modelMapper);
}
