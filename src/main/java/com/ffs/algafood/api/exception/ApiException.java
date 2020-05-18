package com.ffs.algafood.api.exception;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author francisco
 */
@Getter
public class ApiException {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ApiException(String message, HttpStatus status, HttpServletRequest request) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = request.getRequestURI();
    }
}
