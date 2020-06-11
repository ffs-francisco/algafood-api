package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}
