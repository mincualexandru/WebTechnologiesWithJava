package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.symptom.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    void save(Answer answer);

    Answer findById(Long answerId);

    void delete(Answer answer);

    List<Answer> findAll();

}
