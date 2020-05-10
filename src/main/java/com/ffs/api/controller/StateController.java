package com.ffs.api.controller;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.State;
import com.ffs.api.domain.repository.StateRepository;
import com.ffs.api.domain.service.StateRegistrationService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<State> getById(@PathVariable final Long stateId) {
        final var state = stateRepository.findById(stateId);
        if (state.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(state.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody final State state) {
        return stateRegistrationService.save(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(@PathVariable final Long stateId, @RequestBody final State state) {
        final var stateSaved = stateRepository.findById(stateId);
        if (stateSaved.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(state, stateSaved.get(), "id");
        return ResponseEntity.ok(stateRegistrationService.save(stateSaved.get()));
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<String> remove(@PathVariable final Long stateId) {
        try {
            stateRegistrationService.delete(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
