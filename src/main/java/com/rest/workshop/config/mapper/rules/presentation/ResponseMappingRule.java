package com.rest.workshop.config.mapper.rules.presentation;

import com.rest.workshop.config.mapper.EntityDtoMappingRule;
import com.rest.workshop.config.mapper.ModelMapperConfig;
import com.rest.workshop.domain.entitties.symptom.*;
import com.rest.workshop.presentation.dtos.symptom.evaluation.CreateEvaluationDto;
import com.rest.workshop.services.SymptomService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ResponseMappingRule implements EntityDtoMappingRule, ModelMapperConfig.Rule {

    private final SymptomService symptomService;

    public ResponseMappingRule(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @Override
    public void entityToDtoMapping(ModelMapper modelMapper) {

    }

    @Override
    public void dtoToEntityMapping(ModelMapper modelMapper) {

        Converter<Long, Symptom> idToReport =
                context -> symptomService.findById(context.getSource());

        modelMapper.addMappings(new PropertyMap<CreateEvaluationDto, Evaluation>() {
            @Override
            protected void configure() {
                using(idToReport).map(source.symptomId, destination.getSymptom());
            }
        });

    }
}
