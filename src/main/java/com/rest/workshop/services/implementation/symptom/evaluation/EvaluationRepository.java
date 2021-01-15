package com.rest.workshop.services.implementation.symptom.evaluation;

import com.rest.workshop.domain.entitties.symptom.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findAllByRespondentId(Long respondentId);

    List<Evaluation> findAllBySymptomId(Long surveyId);

    Optional<Evaluation> findByRespondentIdAndId(Long respondentId, Long surveyId);

    Optional<Evaluation> findByRespondentIdAndSymptomId(Long respondentId, Long reportId);
}
