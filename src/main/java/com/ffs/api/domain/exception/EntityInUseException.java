package com.ffs.api.domain.exception;

/**
 *
 * @author francisco
 */
public class EntityInUseException extends RuntimeException {

  public EntityInUseException(final String message) {
    super(message);
  }
}
