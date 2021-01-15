package com.rest.workshop.services.implementation.illness;

import com.rest.workshop.domain.entitties.illness.Illness;
import com.rest.workshop.domain.search_params.IllnessSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IllnessSpecsRepo {
    Page<Illness> findAll(
            Pageable page,
            IllnessSearchParams searchParams
    );
}
