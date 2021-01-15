package com.rest.workshop.config.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ModelMapperConfig implements WebMvcConfigurer {

    private final List<Rule> mappingRuleList;

    public ModelMapperConfig(List<Rule> mappingRuleList) {
        this.mappingRuleList = mappingRuleList;
    }

    @Bean
    public ModelMapper getBeanModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setImplicitMappingEnabled(true)
                .setFullTypeMatchingRequired(true)
                .setPropertyCondition(Conditions.isNotNull())
                .setSkipNullEnabled(true);

        configureMappings(modelMapper);

        modelMapper.addConverter((Converter<String, Integer>) context -> Integer.valueOf(context.getSource()));

        return modelMapper;
    }

    private void configureMappings(ModelMapper modelMapper) {
        mappingRuleList.forEach(mappingRule -> mappingRule.addMappings(modelMapper));
    }

    public interface Rule extends MappingRule {

    }

}
