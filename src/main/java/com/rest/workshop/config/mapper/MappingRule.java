package com.rest.workshop.config.mapper;

import org.modelmapper.ModelMapper;

public interface MappingRule {

    void addMappings(ModelMapper modelMapper);

}
