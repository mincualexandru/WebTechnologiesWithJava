package com.rest.workshop.config.mapper.rules.presentation;

import com.rest.workshop.config.mapper.EntityDtoMappingRule;
import com.rest.workshop.config.mapper.ModelMapperConfig;
import com.rest.workshop.domain.entitties.symptom.Answer;
import com.rest.workshop.domain.entitties.symptom.Question;
import com.rest.workshop.domain.entitties.symptom.RespondentAnswer;
import com.rest.workshop.presentation.dtos.symptom.respondent_answer.CreateRespondentAnswerDto;
import com.rest.workshop.services.AnswerService;
import com.rest.workshop.services.QuestionService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class RespondentAnswerMappingRule implements EntityDtoMappingRule, ModelMapperConfig.Rule {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public RespondentAnswerMappingRule(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public void entityToDtoMapping(ModelMapper modelMapper) {

    }

    @Override
    public void dtoToEntityMapping(ModelMapper modelMapper) {

        Converter<Long, Question> idToQuestion =
                context -> questionService.findById(context.getSource());

        Converter<Long, Answer> idToAnswer =
                context -> answerService.findById(context.getSource());

        modelMapper.addMappings(new PropertyMap<CreateRespondentAnswerDto, RespondentAnswer>() {
            @Override
            protected void configure() {
                using(idToQuestion).map(source.questionId, destination.getQuestion());
                using(idToAnswer).map(source.answerId, destination.getAnswer());
            }
        });

    }
}
