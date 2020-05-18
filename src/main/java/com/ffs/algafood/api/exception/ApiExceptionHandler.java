package com.ffs.algafood.api.exception;

import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFound(EntityNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ApiException(e.getMessage(), NOT_FOUND, request));
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handlerEntityInUseException(EntityInUseException e, HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT)
                .body(new ApiException(e.getMessage(), CONFLICT, request));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusinessException(BusinessException e, HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiException(e.getMessage(), BAD_REQUEST, request));
    }
}
