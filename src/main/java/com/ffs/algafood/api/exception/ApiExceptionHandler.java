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

    private final String MSG_USER_GENERIC_ERROR
            = "An unexpected internal system error has occurred. Try again and if the problem persists, contact your system administrator.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
        /**
         * Print the Stack Trace on the console while a logginf mechaninsm is
         * not implemented.
         */
        ex.printStackTrace();

        return this.buildHandlerException(ex, this.MSG_USER_GENERIC_ERROR, this.MSG_USER_GENERIC_ERROR, INTERNAL_SYSTEM_ERROR, INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFound(EntityNotFoundException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), RESOURCE_NOT_FOUND, NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handlerEntityInUse(EntityInUseException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), ENTITY_IN_USE, CONFLICT, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusiness(BusinessException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), ERROR_BUSINESS, BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new ApiException(this.MSG_USER_GENERIC_ERROR, status, request);
        } else if (body instanceof String) {
            body = new ApiException((String) body, this.MSG_USER_GENERIC_ERROR, status, request);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, request);
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

        return this.buildHandlerException(ex, detail, this.MSG_USER_GENERIC_ERROR, INCOMPREHENSIBLE_MESSAGE, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var detail = String.format("The resource '%s' requested, does not exist.",
                ex.getRequestURL());

        return this.buildHandlerException(ex, detail, detail, RESOURCE_NOT_FOUND, headers, status, request);
    }

    private ResponseEntity<Object> buildHandlerException(Exception ex, String detail, String message, ApiExceptionType type, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var apiException = new ApiException(detail, message, type, status, request);
        return this.handleExceptionInternal(ex, apiException, headers, status, request);
    }

    private ResponseEntity<Object> buildHandlerException(Exception ex, String detail, String message, ApiExceptionType type, HttpStatus status, WebRequest request) {
        return this.buildHandlerException(ex, detail, message, type, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        final var detail = String.format("The URL parameter '%s' received a value of '%s', which is of type invalid. Enter a '%s' type value.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        return this.buildHandlerException(ex, detail, this.MSG_USER_GENERIC_ERROR, INVALID_PARAMETER, headers, BAD_REQUEST, request);
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
}
