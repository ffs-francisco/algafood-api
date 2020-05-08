package com.ffs.api.controller;

import com.ffs.api.domain.model.State;
import com.ffs.api.domain.repository.StateRepository;
import com.ffs.api.domain.service.StateRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegistrationService stateRegistrationService;

    @GetMapping
    public List<State> listAll() {
        return this.stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> getById(@PathVariable Long stateId) {
        var state = stateRepository.findById(stateId);
        if (state == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return stateRegistrationService.save(state);
    }
}
