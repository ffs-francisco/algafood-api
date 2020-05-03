package com.ffs.api.infrastructor.repository;

import com.ffs.api.domain.model.City;
import com.ffs.api.domain.repository.CityRepository;
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
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public City save(final City city) {
        return this.manager.merge(city);
    }

    @Override
    @Transactional
    public void delete(final City city) {
        this.manager.remove(this.findById(city.getId()));
    }

    @Override
    public List<City> findAll() {
        return this.manager.createQuery("FROM City", City.class).getResultList();
    }

    @Override
    public City findById(Long id) {
        return this.manager.find(City.class, id);
    }
}
