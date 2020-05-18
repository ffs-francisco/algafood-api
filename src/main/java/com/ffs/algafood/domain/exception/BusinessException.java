package com.ffs.algafood.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 *
 * @author francisco
 */
@ResponseStatus(BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(String reason) {
        super(reason);
    }

    public BusinessException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
