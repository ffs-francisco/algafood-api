package com.ffs.algafood.api.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author francisco
 */
@Getter
@Builder
public class ApiException {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
}
