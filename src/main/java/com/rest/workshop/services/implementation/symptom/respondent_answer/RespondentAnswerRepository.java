package com.rest.workshop.services.implementation.symptom.respondent_answer;

import com.rest.workshop.domain.entitties.symptom.RespondentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondentAnswerRepository extends JpaRepository<RespondentAnswer, Long> {
}
