package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.City;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface CityRepository {

    List<City> findAll();

    City findById(Long id);

    City save(final City city);

    void delete(final City city);
}
