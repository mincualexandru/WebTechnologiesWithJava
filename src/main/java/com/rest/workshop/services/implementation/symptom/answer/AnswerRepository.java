package com.rest.workshop.services.implementation.symptom.answer;

import com.rest.workshop.domain.entitties.symptom.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
