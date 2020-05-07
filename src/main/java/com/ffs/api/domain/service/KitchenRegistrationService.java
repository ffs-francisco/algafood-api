package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class KitchenRegistrationService {

  @Autowired
  private KitchenRepository kitchenRepository;

  public Kitchen save(final Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  public void delete(final Long kitchenId) {
    try {
      kitchenRepository.delete(kitchenId);
    } catch (EmptyResultDataAccessException ex) {
      throw new EntityNotFoundException(
              String.format("Não exsiste um cadastro de cozinha com código %d", kitchenId));
    } catch (DataIntegrityViolationException ex) {
      throw new EntityInUseException(
              String.format("Cozinha de código %d não pode ser removida, pois já está em uso", kitchenId));
    }
  }
}
