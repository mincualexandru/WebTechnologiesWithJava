package com.rest.workshop.presentation;

import com.rest.workshop.presentation.dtos.symptom.evaluation.EvaluationDto;
import com.rest.workshop.services.EvaluationService;
import com.rest.workshop.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = EvaluationController.RESPONSES_API_URL)
public class EvaluationController {

    public static final String RESPONSES_API_URL = Constants.BASE_API_URI + "/evaluations";

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(evaluationService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{evaluationId}")
//    @PreAuthorize("@responseServiceImpl.checkResponseFromUser(#responseId)")
    public ResponseEntity<EvaluationDto> viewResponse(@PathVariable Long evaluationId) {
        return new ResponseEntity<>(evaluationService.getById(evaluationId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{evaluationId}")
    @PreAuthorize("@evaluationServiceImpl.checkEvaluationFromUser(#evaluationId)")
    public ResponseEntity<Void> deleteDocuments(@PathVariable Long evaluationId) {
        evaluationService.deleteById(evaluationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
