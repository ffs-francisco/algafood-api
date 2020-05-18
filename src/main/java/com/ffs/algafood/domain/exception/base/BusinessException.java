package com.ffs.algafood.domain.exception.base;

/**
 *
 * @author francisco
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String reason) {
        super(reason);
    }

    public BusinessException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
