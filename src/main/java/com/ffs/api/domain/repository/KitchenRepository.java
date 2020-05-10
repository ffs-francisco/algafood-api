package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Kitchen;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author francisco
 */
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> findAllByNameContaining(String name);

    Optional<Kitchen> findByName(String name);

    boolean existsByName(String name);
}
