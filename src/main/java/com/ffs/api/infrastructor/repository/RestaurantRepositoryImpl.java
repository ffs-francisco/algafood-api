package com.ffs.api.infrastructor.repository;

import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.RestaurantRepository;
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
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Restaurant save(final Restaurant restaurant) {
        return this.manager.merge(restaurant);
    }

    @Override
    @Transactional
    public void delete(final Restaurant restaurant) {
        this.manager.remove(this.findById(restaurant.getId()));
    }

    @Override
    public List<Restaurant> findAll() {
        return this.manager.createQuery("FROM Restaurant", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findById(Long id) {
        return this.manager.find(Restaurant.class, id);
    }
}
