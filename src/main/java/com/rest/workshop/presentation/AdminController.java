package com.rest.workshop.presentation;

import com.rest.workshop.presentation.dtos.illness.CreateIllnessDto;
import com.rest.workshop.presentation.dtos.illness.IllnessDto;
import com.rest.workshop.presentation.dtos.symptom.CreateSymptomDto;
import com.rest.workshop.presentation.dtos.symptom.SymptomDto;
import com.rest.workshop.presentation.dtos.symptom.UpdateSymptomDto;
import com.rest.workshop.presentation.dtos.user.CreateUserDto;
import com.rest.workshop.presentation.dtos.user.UpdateEnabledDto;
import com.rest.workshop.presentation.dtos.user.UserDto;
import com.rest.workshop.presentation.dtos.user.UserListingDto;
import com.rest.workshop.services.IllnessService;
import com.rest.workshop.services.SymptomService;
import com.rest.workshop.services.UserService;
import com.rest.workshop.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = AdminController.ADMIN_API_URI)
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    public static final String ADMIN_API_URI = Constants.BASE_API_URI + "/admin";

    private final UserService userService;
    private final SymptomService symptomService;
    private final IllnessService illnessService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, SymptomService symptomService, IllnessService illnessService, ModelMapper modelMapper) {
        this.userService = userService;
        this.symptomService = symptomService;
        this.illnessService = illnessService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "users")
    @ResponseBody
    public ResponseEntity<List<UserListingDto>> getAll() {
        return new ResponseEntity<>(userService.findAll(modelMapper), HttpStatus.OK);
    }

    @PostMapping(value = "users")
    @ResponseBody
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.create(modelMapper, createUserDto), HttpStatus.OK);
    }

    @PutMapping(value = "users/{userId}/enabled")
    @ResponseBody
    public ResponseEntity<UserDto> updateEnabled(@Valid @RequestBody() UpdateEnabledDto updates,
                                                 @PathVariable Long userId) {
        return new ResponseEntity<>(userService.updateEnabled(modelMapper, userId, updates), HttpStatus.OK);
    }

    @PostMapping(value = "illnesses")
    public ResponseEntity<IllnessDto> createIllness(@Valid @RequestBody CreateIllnessDto createIllnessDto) {
        return new ResponseEntity<>(illnessService.create(modelMapper, createIllnessDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "illnesses/{illnessId}")
    public ResponseEntity<IllnessDto> updateIllness(@PathVariable Long illnessId, @Valid @RequestBody CreateIllnessDto createIllnessDto) {
        return new ResponseEntity<>(illnessService.update(modelMapper, illnessId, createIllnessDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "illnesses/{illnessId}")
    public ResponseEntity<Void> deleteIllness(@PathVariable Long illnessId) {
        illnessService.deleteById(illnessId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "symptoms")
    public ResponseEntity<SymptomDto> createSymptom(@Valid @RequestBody CreateSymptomDto createSymptomDto) {
        return new ResponseEntity<>(symptomService.create(modelMapper, createSymptomDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "symptoms/{symptomId}")
    public ResponseEntity<SymptomDto> updateSymptom(@PathVariable Long symptomId, @Valid @RequestBody UpdateSymptomDto updateSymptomDto) {
        return new ResponseEntity<>(symptomService.update(modelMapper, symptomId, updateSymptomDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "symptoms/open")
    public ResponseEntity<?> geAll() {
        return new ResponseEntity<>(symptomService.viewAllOpen(modelMapper), HttpStatus.OK);
    }

    @PutMapping(value = "symptoms/{symptomId}/enabled")
    @PreAuthorize("@symptomServiceImpl.checkIfUserIsOwner(#symptomId)")
    public ResponseEntity<?> updateAvailability(@PathVariable Long symptomId, @RequestBody() UpdateEnabledDto updates) {
        return new ResponseEntity<>(symptomService.updateAvailability(modelMapper, symptomId, updates), HttpStatus.CREATED);
    }

}
