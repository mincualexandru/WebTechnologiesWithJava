package com.rest.workshop.services.implementation.symptom;

import com.rest.workshop.domain.entitties.symptom.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
