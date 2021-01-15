package com.rest.workshop.services.implementation.illness;

import com.rest.workshop.domain.entitties.illness.Illness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessRepository extends IllnessSpecsRepo, JpaRepository<Illness, Long>,
        JpaSpecificationExecutor<Illness> {
}
