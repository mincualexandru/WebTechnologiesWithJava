package com.rest.workshop.presentation;

import com.rest.workshop.presentation.dtos.symptom.SymptomDto;
import com.rest.workshop.services.SymptomService;
import com.rest.workshop.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SymptomController.REPORTS_API_URL)
public class SymptomController {

    public static final String REPORTS_API_URL = Constants.BASE_API_URI + "/symptoms";

    private final SymptomService symptomService;
    private final ModelMapper modelMapper;

    public SymptomController(SymptomService symptomService, ModelMapper modelMapper) {
        this.symptomService = symptomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{symptomId}")
    @PreAuthorize("@symptomServiceImpl.checkOwnerOfTheReport(#symptomId)")
    public ResponseEntity<SymptomDto> getById(@PathVariable Long symptomId) {
        return new ResponseEntity<>(symptomService.getById(modelMapper, symptomId), HttpStatus.OK);
    }

}
