package com.rest.workshop.presentation;

import com.rest.workshop.domain.search_params.IllnessSearchParams;
import com.rest.workshop.presentation.dtos.illness.IllnessDto;
import com.rest.workshop.presentation.dtos.symptom.SymptomDto;
import com.rest.workshop.services.IllnessService;
import com.rest.workshop.services.SymptomService;
import com.rest.workshop.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = IllnessController.ILLNESS_API_URL)
public class IllnessController {

    public static final String ILLNESS_API_URL = Constants.BASE_API_URI + "/illnesses";

    private final IllnessService illnessService;
    private final ModelMapper modelMapper;

    public IllnessController(IllnessService illnessService, ModelMapper modelMapper) {
        this.illnessService = illnessService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{illnessId}")
    public ResponseEntity<IllnessDto> getById(@PathVariable Long illnessId) {
        return new ResponseEntity<>(illnessService.getById(modelMapper, illnessId), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Page<IllnessDto>> getPage(IllnessSearchParams searchParams,
                                                    @PageableDefault Pageable page) {
        return new ResponseEntity<>(illnessService.findAll(modelMapper, page, searchParams), HttpStatus.OK);
    }

}