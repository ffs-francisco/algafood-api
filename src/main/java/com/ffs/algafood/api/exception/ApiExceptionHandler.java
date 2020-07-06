package com.ffs.algafood.api.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.ffs.algafood.api.exception.model.ApiException;
import com.ffs.algafood.api.exception.model.ObjectError;
import com.ffs.algafood.api.exception.model.Type;
import com.ffs.algafood.core.validation.ValidationException;
import com.ffs.algafood.domain.exception.base.AttributeInUseException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.ffs.algafood.api.exception.model.Type.*;
import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
        final var message = messageSource.getMessage("default", null, request.getLocale());

        /**
         * Print the Stack Trace on the console while a logginf mechaninsm is not implemented.
         */
        ex.printStackTrace();

        return this.buildHandlerException(ex, message, message, INTERNAL_SYSTEM_ERROR, INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFound(EntityNotFoundException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), RESOURCE_NOT_FOUND, NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handlerEntityInUse(EntityInUseException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), ENTITY_IN_USE, CONFLICT, request);
    }

    @ExceptionHandler(AttributeInUseException.class)
    public ResponseEntity<?> handlerEntityInUse(AttributeInUseException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), ENTITY_IN_USE, CONFLICT, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusiness(BusinessException ex, WebRequest request) {
        return this.buildHandlerException(ex, ex.getMessage(), ex.getMessage(), ERROR_BUSINESS, BAD_REQUEST, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationInternal(ValidationException ex, WebRequest request) {
        return handlerArgumentNotValid(ex, ex.getBindingResult(), new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handlerArgumentNotValid(ex, ex.getBindingResult(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handlerArgumentNotValid(ex, ex.getBindingResult(), headers, status, request);
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
        final var message = messageSource.getMessage("default", null, request.getLocale());
        var detail = messageSource.getMessage("http-message-not-readable", null, request.getLocale());

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            detail = this.detailInvalidFormat((InvalidFormatException) rootCause, request);
        } else if (rootCause instanceof PropertyBindingException) {
            detail = this.detailPropertyBinding((PropertyBindingException) rootCause, request);
        }

        return this.buildHandlerException(ex, detail, message, INCOMPREHENSIBLE_MESSAGE, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var args = new Object[]{ex.getRequestURL()};
        final var detail = messageSource.getMessage("no-handler-found", args, request.getLocale());

        return this.buildHandlerException(ex, detail, detail, RESOURCE_NOT_FOUND, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var message = messageSource.getMessage("default", null, request.getLocale());

        if (body == null) {
            body = new ApiException(message, status, request);
        } else if (body instanceof String) {
            body = new ApiException((String) body, message, status, request);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handlerArgumentNotValid(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatus status, WebRequest request) throws NoSuchMessageException {
        final var detail = messageSource.getMessage("method-argument-not-valid", null, request.getLocale());
        final var problemObjects = bindingResult.getAllErrors().stream().
                map(objectError -> {
                    final var errorMessage = messageSource.getMessage(objectError, request.getLocale());
                    var errorName = objectError.getObjectName();
                    if (objectError instanceof FieldError) {
                        errorName = ((FieldError) objectError).getField();
                    }

                    return ObjectError.builder()
                            .name(errorName)
                            .userMessage(errorMessage)
                            .build();
                })
                .collect(Collectors.toList());

        return this.buildHandlerException(ex, detail, detail, problemObjects, INVALID_DATA, headers, status, request);
    }

    private ResponseEntity<Object> buildHandlerException(Exception ex, String detail, String message, List<ObjectError> fields, Type type, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var apiException = new ApiException(detail, message, fields, type, status, request);
        return this.handleExceptionInternal(ex, apiException, headers, status, request);
    }

    private ResponseEntity<Object> buildHandlerException(Exception ex, String detail, String message, Type type, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.buildHandlerException(ex, detail, message, new ArrayList<>(), type, headers, status, request);
    }

    private ResponseEntity<Object> buildHandlerException(Exception ex, String detail, String message, Type type, HttpStatus status, WebRequest request) {
        return this.buildHandlerException(ex, detail, message, type, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        final var args = new Object[]{ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()};
        final var detail = messageSource.getMessage("method-argument-type-mismatch", args, request.getLocale());
        final var message = messageSource.getMessage("default", null, request.getLocale());

        return this.buildHandlerException(ex, detail, message, INVALID_PARAMETER, headers, BAD_REQUEST, request);
    }

    private String detailInvalidFormat(InvalidFormatException ex, WebRequest request) {
        final var args = new Object[]{this.joinPath(ex), ex.getValue(), ex.getTargetType().getSimpleName()};
        return messageSource.getMessage("invalid-format", args, request.getLocale());
    }

    private String detailPropertyBinding(PropertyBindingException ex, WebRequest request) {
        final var args = new Object[]{this.joinPath(ex)};
        return messageSource.getMessage("property-binding", args, request.getLocale());
    }

    private String joinPath(MismatchedInputException ex) {
        return ex.getPath().stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
    }
}
