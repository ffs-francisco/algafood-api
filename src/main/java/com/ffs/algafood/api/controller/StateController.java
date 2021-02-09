package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.state.StateRequest;
import com.ffs.algafood.api.model.response.state.StateResponse;
import com.ffs.algafood.domain.model.State;
import com.ffs.algafood.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<StateResponse> listAll() {
        return StateResponse.fromList(this.stateService.findAll());
    }

    @GetMapping("/{stateId}")
    @ResponseStatus(OK)
    public StateResponse getById(@PathVariable final Long stateId) {
        return StateResponse.from(stateService.findById(stateId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public StateResponse add(@RequestBody @Valid final StateRequest stateRequest) {
        return StateResponse.from(stateService.save(stateRequest.toModel()));
    }

    @PutMapping("/{stateId}")
    @ResponseStatus(OK)
    public State update(@PathVariable final Long stateId, @RequestBody @Valid final StateRequest stateRequest) {
        final var stateSaved = stateService.findById(stateId);

        stateRequest.copyPropertiesTo(stateSaved);
        return stateService.save(stateSaved);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(NO_CONTENT)
    public void remove(@PathVariable final Long stateId) {
        stateService.delete(stateId);
    }
}
