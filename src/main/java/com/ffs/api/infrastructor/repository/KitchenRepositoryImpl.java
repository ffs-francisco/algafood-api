package com.ffs.api.infrastructor.repository;

import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Component
public class KitchenRepositoryImpl implements KitchenRepository {

  @PersistenceContext
  private EntityManager manager;

  @Override
  @Transactional
  public Kitchen save(final Kitchen kitchen) {
    return this.manager.merge(kitchen);
  }

  @Override
  @Transactional
  public void delete(final Long id) {
    var kitchen = this.findById(id);
    if (kitchen == null) {
      throw new EmptyResultDataAccessException(1);
    }
    
    this.manager.remove(kitchen);
  }

  @Override
  public List<Kitchen> findAll() {
    return this.manager.createQuery("FROM Kitchen", Kitchen.class).getResultList();
  }

  @Override
  public Kitchen findById(Long id) {
    return this.manager.find(Kitchen.class, id);
  }
}
