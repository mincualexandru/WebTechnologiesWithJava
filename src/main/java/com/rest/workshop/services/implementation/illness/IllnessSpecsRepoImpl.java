package com.rest.workshop.services.implementation.illness;

import com.rest.workshop.domain.entitties.illness.Diagnosis;
import com.rest.workshop.domain.entitties.illness.Illness;
import com.rest.workshop.domain.entitties.illness.Illness_;
import com.rest.workshop.domain.entitties.illness.Risk;
import com.rest.workshop.domain.entitties.symptom.Symptom;
import com.rest.workshop.domain.search_params.IllnessSearchParams;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class IllnessSpecsRepoImpl implements IllnessSpecsRepo {

    private final IllnessRepository illnessRepository;

    public IllnessSpecsRepoImpl(@Lazy IllnessRepository illnessRepository) {
        this.illnessRepository = illnessRepository;
    }

    @Override
    public Page<Illness> findAll(Pageable page, IllnessSearchParams searchParams) {
        Specification<Illness> spec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            List<Symptom> symptoms = searchParams.getSymptoms();
            if (symptoms != null) {
                Join<Illness, Symptom> symptomJoin = root.join(Illness_.symptoms);
                CriteriaBuilder.In<Symptom> symptomIn = cb.in(symptomJoin);
                for (Symptom risk : symptoms) {
                    symptomIn.value(risk);
                }

                predicate = cb.and(predicate, symptomIn);
            }

            List<Risk> risks = searchParams.getRisks();
            if (risks != null) {
                Join<Illness, Risk> riskJoin = root.join(Illness_.risks);
                CriteriaBuilder.In<Risk> riskIn = cb.in(riskJoin);
                for (Risk risk : risks) {
                    riskIn.value(risk);
                }

                predicate = cb.and(predicate, riskIn);
            }

            List<Diagnosis> diagnoses = searchParams.getDiagnoses();
            if (diagnoses != null) {
                Join<Illness, Diagnosis> diagnosisJoin = root.join(Illness_.diagnoses);
                CriteriaBuilder.In<Diagnosis> diagnosisIn = cb.in(diagnosisJoin);
                for (Diagnosis diagnosis : diagnoses) {
                    diagnosisIn.value(diagnosis);
                }

                predicate = cb.and(predicate, diagnosisIn);
            }

            return predicate;
        };

        return illnessRepository.findAll(spec, page);
    }
}
