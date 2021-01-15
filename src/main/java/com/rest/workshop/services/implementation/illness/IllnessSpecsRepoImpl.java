package com.rest.workshop.services.implementation.illness;

import com.rest.workshop.domain.entitties.illness.Illness;
import com.rest.workshop.domain.search_params.IllnessSearchParams;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class IllnessSpecsRepoImpl implements IllnessSpecsRepo {

    private final IllnessRepository illnessRepository;

    public IllnessSpecsRepoImpl(@Lazy IllnessRepository illnessRepository) {
        this.illnessRepository = illnessRepository;
    }

    @Override
    public Page<Illness> findAll(Pageable page, IllnessSearchParams searchParams) {
        Specification<Illness> spec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            return predicate;
        };

        return illnessRepository.findAll(spec, page);
    }
}
