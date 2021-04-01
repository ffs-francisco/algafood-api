package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
