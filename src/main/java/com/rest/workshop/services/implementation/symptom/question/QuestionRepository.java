package com.rest.workshop.services.implementation.symptom.question;

import com.rest.workshop.domain.entitties.symptom.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
