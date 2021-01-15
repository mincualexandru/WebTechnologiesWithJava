package com.rest.workshop.presentation;

import com.rest.workshop.presentation.dtos.symptom.evaluation.CreateEvaluationDto;
import com.rest.workshop.presentation.dtos.symptom.evaluation.EvaluationDto;
import com.rest.workshop.services.EvaluationService;
import com.rest.workshop.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.USERS_API_URL)
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    public static final String USERS_API_URL = Constants.BASE_API_URI + "/users";

    private final EvaluationService evaluationService;

    public UserController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping(value="/evaluations")
    public ResponseEntity<EvaluationDto> respondSurvey(@RequestBody CreateEvaluationDto response) {
        return new ResponseEntity<>(evaluationService.create(response), HttpStatus.OK);
    }

}
