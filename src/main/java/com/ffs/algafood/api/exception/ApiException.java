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

    private final LocalDateTime timestamp;
    private final Integer status;
    private final String title;
    private final String detail;
    private final String path;

    public ApiException(String message, HttpStatus status, WebRequest request) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.title = status.getReasonPhrase();
        this.detail = message;
        this.path = request.getDescription(false).substring(4);
    }
}
