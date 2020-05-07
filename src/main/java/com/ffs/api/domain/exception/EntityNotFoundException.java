package com.ffs.api.domain.exception;

/**
 *
 * @author francisco
 */
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final String message) {
    super(message);
  }
}
