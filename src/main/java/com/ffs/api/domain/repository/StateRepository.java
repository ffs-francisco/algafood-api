package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.State;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface StateRepository {

    List<State> findAll();

    State findById(Long id);

    State save(final State state);

    void delete(final State state);
}
