package com.ffs.api.infrastructor.repository;

import com.ffs.api.domain.model.State;
import com.ffs.api.domain.repository.StateRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public State save(final State state) {
        return this.manager.merge(state);
    }

    @Override
    @Transactional
    public void delete(final State state) {
        this.manager.remove(this.findById(state.getId()));
    }

    @Override
    public List<State> findAll() {
        return this.manager.createQuery("FROM State", State.class).getResultList();
    }

    @Override
    public State findById(Long id) {
        return this.manager.find(State.class, id);
    }
}
