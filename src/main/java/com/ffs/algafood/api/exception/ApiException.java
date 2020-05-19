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
    private final String type;
    private final String title;
    private final String detail;
    private final String path;

    public ApiException(String detail, ApiExceptionType type, HttpStatus status, WebRequest request) {
        this.status = status.value();
        this.type = type.getUri();
        this.title = type.getTitle();
        this.detail = detail;

        this.path = request.getDescription(false).substring(4);
    }
}
