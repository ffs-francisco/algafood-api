package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.UserNotFoundException;
import com.ffs.algafood.domain.exception.base.AttributeInUseException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.User;
import com.ffs.algafood.domain.repository.UserRepository;
import com.ffs.algafood.domain.service.permission.GroupService;
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
    @Autowired
    private GroupService groupService;

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

    @Transactional
    public void linkGroup(final Long userId, final Long groupId) throws EntityNotFoundException {
        final var user = this.findById(userId);
        final var group = groupService.findById(groupId);

        user.getGroups().add(group);
    }

    @Transactional
    public void unlinkGroup(final Long userId, final Long groupId) throws EntityNotFoundException {
        final var user = this.findById(userId);
        final var group = groupService.findById(groupId);

        user.getGroups().remove(group);
    }

    private void checkEmailIsInUse(final User user) throws AttributeInUseException {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userSaved -> {
                    if (!userSaved.equals(user)) {
                        throw new AttributeInUseException(User.class, "email", user.getEmail());
                    }
                });
    }

    private void checkCurrentPassword(final User user, final String currentPassword) throws BusinessException {
        if (!user.getPassword().equals(currentPassword)) {
            throw new BusinessException("The current password informe is invalid for this user.");
        }
    }
}
