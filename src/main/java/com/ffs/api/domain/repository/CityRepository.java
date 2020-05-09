package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
