package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.UserNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.model.User;
import com.ffs.algafood.domain.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("id", userId));
    }

    @Transactional
    public User save(final User user) {
        userRepository.detach(user);

        this.checkEmailIsInUse(user);
        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(final Long userId, final String currentPassword, final String newPassword) {
        final var user = this.findById(userId);

        checkCurrentPassword(user, currentPassword);
        user.setPassword(newPassword);
    }

    private void checkEmailIsInUse(final User user) throws BusinessException {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userSaved -> {
                    if (!userSaved.equals(user)) {
                        throw new BusinessException(String.format("Already exist a user with the e-mail %s", user.getEmail()));
                    }
                });
    }

    private void checkCurrentPassword(final User user, final String currentPassword) throws BusinessException {
        if (!user.getPassword().equals(currentPassword)) {
            throw new BusinessException("The current password informe is invalid for this user.");
        }
    }
}
