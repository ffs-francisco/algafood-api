package com.ffs.algafood.api.exception.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author francisco
 */
@Getter
public class ApiException {

    private final LocalDateTime timestamp;
    private final Integer status;
    private final String title;
    private final String detail;

    private final String userMessage;
    private final List<Field> fields;

    private final String path;
    private final String type;

    public ApiException(String detail, String userMessage, List<Field> fields, Type type, HttpStatus status, WebRequest request) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.title = (type == null) ? status.getReasonPhrase() : type.getTitle();
        this.detail = detail;

        this.userMessage = userMessage;
        this.fields = (fields == null) ? new ArrayList<>() : fields;

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
