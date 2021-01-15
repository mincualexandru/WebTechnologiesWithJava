package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.symptom.RespondentAnswer;

import java.util.List;

public interface RespondentAnswerService {
    void save(RespondentAnswer respondentAnswer);

    RespondentAnswer findById(Long id);

    void delete(RespondentAnswer respondentAnswer);

    List<RespondentAnswer> findAll();
}
