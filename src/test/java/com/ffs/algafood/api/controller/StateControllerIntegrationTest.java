package com.ffs.algafood.api.controller;

import com.ffs.algafood.ApplicationContextIntegrationTest;
import com.ffs.algafood.domain.model.State;
import com.ffs.algafood.domain.repository.StateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 * @author francisco
 */
@SpringBootTest
public class StateControllerIntegrationTest extends ApplicationContextIntegrationTest {

    private final String URL = "/states";

    @Autowired
    private StateRepository stateRepository;

    @AfterEach
    public void after() {
        this.stateRepository.deleteAll();
    }

    /**
     * Test to request the entire list of states.
     */
    @Test
    public void givenNoStates_whenStateListing_thenSuccessWithEmptyList200() throws Exception {
        get(URL)
                .andExpect(status().isOk());
    }

    /**
     * Test to request the entire list of states.
     */
    @Test
    public void givenWhitStates_whenStateListing_thenSuccessWithNotEmptyList200() throws Exception {
        this.before();

        get(URL)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    /**
     * Test to request one state by valid ID.
     */
    @Test
    public void givenWithOneState_whenStateRequestedById_theSuccess200() throws Exception {
        this.before();

        var stateSaved = this.stateRepository.findAll().get(0);

        get(String.format("%s/%s", URL, stateSaved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maranhão"));
    }

    /**
     * Test to request one state by invalid ID.
     */
    @Test
    public void givenWithOneState_whenStateRequestedByInvalidId_theError404() throws Exception {
        this.before();

        get(String.format("%s/%s", URL, 100))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    private void before() {
        var stateMock = new State();
        stateMock.setName("Maranhão");

        this.stateRepository.save(stateMock);
    }
}
