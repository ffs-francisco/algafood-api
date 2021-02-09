package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author francisco
 */
@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}
