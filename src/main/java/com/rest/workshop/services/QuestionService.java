package com.rest.workshop.services;

import com.rest.workshop.domain.entitties.symptom.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    void save(Question question);

    Question findById(Long questionId);

    void delete(Question question);

    List<Question> findAll();
}
