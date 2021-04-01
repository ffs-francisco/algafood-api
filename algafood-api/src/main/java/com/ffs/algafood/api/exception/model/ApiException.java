package com.ffs.algafood.api.exception.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author francisco
 */
@ApiModel("Exception")
@Getter
public class ApiException {

    @ApiModelProperty(example = "2021-03-24T02:09:29.738263Z", position = 5, required = true)
    private final OffsetDateTime timestamp;

    @ApiModelProperty(example = "400", position = 10, required = true)
    private final Integer status;

    @ApiModelProperty(example = "Invalid Path Parameter.", position = 15, required = true)
    private final String title;

    @ApiModelProperty(example = "Parameter ´Id´ is invalid. Enter a ´Long´ type value.", position = 20, required = true)
    private final String detail;


    @ApiModelProperty(example = "Internal system error has occurred. If the problem persists, contact your system administrator.", position = 25, required = true)
    private final String userMessage;

    @ApiModelProperty(value = "List of objects or fields with problem", position = 30, required = false)
    private final List<ObjectError> objects;


    @ApiModelProperty(value = "/payment-methods/1a", position = 35, required = false)
    private final String path;

    @ApiModelProperty(value = "https://algafood.com.br/invalid-path-parameter", position = 40, required = false)
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
