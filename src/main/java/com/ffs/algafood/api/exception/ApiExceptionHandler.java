package com.ffs.algafood.api.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
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
        final var apiException = new ApiException(ex.getMessage(), RESOURCE_NOT_FOUND, NOT_FOUND, request);
        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handlerEntityInUse(EntityInUseException ex, WebRequest request) {
        final var apiException = new ApiException(ex.getMessage(), ENTITY_IN_USE, CONFLICT, request);
        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), CONFLICT, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusiness(BusinessException ex, WebRequest request) {
        final var apiException = new ApiException(ex.getMessage(), ERROR_BUSINESS, BAD_REQUEST, request);
        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new ApiException(status, request);
        } else if (body instanceof String) {
            body = new ApiException((String) body, status, request);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var detail = "The request body is invalid. Verify syntax errors.";

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            detail = this.detailInvalidFormat((InvalidFormatException) rootCause);
        } else if (rootCause instanceof PropertyBindingException) {
            detail = this.detailPropertyBinding((PropertyBindingException) rootCause);
        }

        final var apiException = new ApiException(detail, INCOMPREHENSIBLE_MESSAGE, BAD_REQUEST, request);
        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var detail = String.format("The resource '%s' requested, does not exist.",
                ex.getRequestURL());

        final var apiException = new ApiException(detail, RESOURCE_NOT_FOUND, NOT_FOUND, request);
        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), NOT_FOUND, request);
    }

    private String detailInvalidFormat(InvalidFormatException ex) {
        return String.format("The property '%s' received a value of '%s', which is of type invalid. Enter a '%s' type value.",
                this.joinPath(ex), ex.getValue(), ex.getTargetType().getSimpleName());
    }

    private String detailPropertyBinding(PropertyBindingException ex) {
        return String.format("The property '%s' not exist. Correct or remove it and try again.",
                this.joinPath(ex));
    }

    private String joinPath(MismatchedInputException ex) {
        return ex.getPath().stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        final var detail = String.format("The URL parameter '%s' received a value of '%s', which is of type invalid. Enter a '%s' type value.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        final var apiException = new ApiException(detail, INVALID_PARAMETER, BAD_REQUEST, request);
        return this.handleExceptionInternal(ex, apiException, new HttpHeaders(), BAD_REQUEST, request);
    }
}
