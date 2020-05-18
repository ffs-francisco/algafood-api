package com.ffs.algafood.api.controller;

import com.ffs.algafood.domain.model.State;
import com.ffs.algafood.domain.service.StateService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    @ResponseStatus(OK)
    public List<State> listAll() {
        return this.stateService.findAll();
    }

    @GetMapping("/{stateId}")
    @ResponseStatus(OK)
    public State getById(@PathVariable final Long stateId) {
        return stateService.findById(stateId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public State add(@RequestBody final State state) {
        return stateService.save(state);
    }

    @PutMapping("/{stateId}")
    @ResponseStatus(OK)
    public State update(@PathVariable final Long stateId, @RequestBody final State state) {
        final var stateSaved = stateService.findById(stateId);

        BeanUtils.copyProperties(state, stateSaved, "id");
        return stateService.save(stateSaved);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(NO_CONTENT)
    public void remove(@PathVariable final Long stateId) {
        stateService.delete(stateId);
    }
}
