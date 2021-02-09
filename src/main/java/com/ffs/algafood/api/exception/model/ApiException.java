package com.ffs.algafood.api.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francisco
 */
@Getter
public class ApiException {

    private final OffsetDateTime timestamp;
    private final Integer status;
    private final String title;
    private final String detail;

    private final String userMessage;
    private final List<ObjectError> objects;

    private final String path;
    private final String type;

    public ApiException(String detail, String userMessage, List<ObjectError> errors, Type type, HttpStatus status, WebRequest request) {
        this.timestamp = OffsetDateTime.now();
        this.status = status.value();
        this.title = (type == null) ? status.getReasonPhrase() : type.getTitle();
        this.detail = detail;

        this.userMessage = userMessage;
        this.objects = (errors == null) ? new ArrayList<>() : errors;

        this.type = (type == null) ? "" : type.getUri();
        this.path = request.getDescription(false).substring(4);
    }

    public ApiException(String detail, String userMessage, Type type, HttpStatus status, WebRequest request) {
        this(detail, userMessage, null, type, status, request);
    }

    public ApiException(String userMessage, HttpStatus status, WebRequest request) {
        this(status.getReasonPhrase(), userMessage, null, null, status, request);
    }

    public ApiException(String detail, String userMessage, HttpStatus status, WebRequest request) {
        this(detail, userMessage, null, null, status, request);
    }
}
