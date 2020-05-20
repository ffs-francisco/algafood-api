package com.ffs.algafood.api.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.ffs.algafood.api.exception.ApiExceptionType.*;
import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFound(EntityNotFoundException ex, WebRequest request) {
        final var apiException = new ApiException(ex.getMessage(), ENTITY_NOT_FOUND, NOT_FOUND, request);

        return super.handleExceptionInternal(ex, apiException, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handlerEntityInUseException(EntityInUseException ex, WebRequest request) {
        final var apiException = new ApiException(ex.getMessage(), ENTITY_IN_USE, CONFLICT, request);

        return super.handleExceptionInternal(ex, apiException, new HttpHeaders(), CONFLICT, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusinessException(BusinessException ex, WebRequest request) {
        final var apiException = new ApiException(ex.getMessage(), ERROR_BUSINESS, BAD_REQUEST, request);

        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var detail = "The request body is invalid. Verify syntax errors.";

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            detail = this.detailInvalidFormatException((InvalidFormatException) rootCause);
        } else if (rootCause instanceof PropertyBindingException) {
            detail = this.detailPropertyBindingException((PropertyBindingException) rootCause);
        }

        final var apiException = new ApiException(detail, INCOMPREHENSIBLE_MESSAGE, BAD_REQUEST, request);
        return super.handleExceptionInternal(ex, apiException, new HttpHeaders(), BAD_REQUEST, request);
    }

    private String detailInvalidFormatException(InvalidFormatException ex) {
        return String.format("The property '%s' received a value of '%s', which is of type invalid. Enter a '%s' type value.",
                this.joinPath(ex), ex.getValue(), ex.getTargetType().getSimpleName());
    }

    private String detailPropertyBindingException(PropertyBindingException ex) {
        return String.format("The property '%s' not exist. Correct or remove it and try again.",
                this.joinPath(ex));
    }

    private String joinPath(MismatchedInputException ex) {
        return ex.getPath().stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
    }
}
