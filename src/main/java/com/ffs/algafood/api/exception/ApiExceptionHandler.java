package com.ffs.algafood.api.exception;

import com.ffs.algafood.domain.exception.BusinessException;
import com.ffs.algafood.domain.exception.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 *
 * @author francisco
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandlerNotFound(EntityNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ApiException(e.getMessage(), NOT_FOUND, request));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> exceptionHandlerBadRequest(BusinessException e, HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiException(e.getMessage(), BAD_REQUEST, request));
    }
}
