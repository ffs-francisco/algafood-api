package com.ffs.algafood.api.exception;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author francisco
 */
@Getter
public class ApiException {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final Integer status;
    private final String title;
    private final String detail;

    private String userMessage;

    private final String path;
    private final String type;

    public ApiException(String detail, String userMessage, ApiExceptionType type, HttpStatus status, WebRequest request) {
        this.status = status.value();
        this.type = (type == null) ? "" : type.getUri();
        this.title = (type == null) ? status.getReasonPhrase() : type.getTitle();
        this.detail = detail;

        this.path = request.getDescription(false).substring(4);

        this.userMessage = userMessage;
    }

    ApiException(String userMessage, HttpStatus status, WebRequest request) {
        this(status.getReasonPhrase(), userMessage, null, status, request);
    }

    ApiException(String detail, String userMessage, HttpStatus status, WebRequest request) {
        this(detail, userMessage, null, status, request);
    }
}
