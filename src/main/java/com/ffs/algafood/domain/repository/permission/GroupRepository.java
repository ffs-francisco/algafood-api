package com.ffs.algafood.domain.repository.permission;

import com.ffs.algafood.domain.model.permission.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
