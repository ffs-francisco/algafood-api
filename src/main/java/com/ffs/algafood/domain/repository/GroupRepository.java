package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
