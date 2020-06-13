package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.GroupNotFoundException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.model.permission.Group;
import com.ffs.algafood.domain.repository.permission.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(final Long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("id", groupId));
    }

    @Transactional
    public Group save(final Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(final Long groupId) throws EntityInUseException, GroupNotFoundException {
        try {
            groupRepository.deleteById(groupId);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new GroupNotFoundException("id", groupId);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(groupId, Group.class);
        }
    }
}
