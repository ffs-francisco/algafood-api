package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
