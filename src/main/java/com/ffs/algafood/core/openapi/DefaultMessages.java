package com.ffs.algafood.core.openapi;

import lombok.Getter;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum DefaultMessages {

    BAD_REQUEST_MESSAGE(
            new ResponseMessageBuilder()
                    .code(BAD_REQUEST.value()).message("Bad Request")
                    .responseModel(new ModelRef("Exception"))
                    .build()
    ),

    INTERNAL_ERROR_MESSAGE(
            new ResponseMessageBuilder()
                    .code(INTERNAL_SERVER_ERROR.value()).message("Internal server error")
                    .responseModel(new ModelRef("Exception"))
                    .build()
    ),

    UNSUPPORTED_MEDIA_TYPE_MESSAGE(
            new ResponseMessageBuilder()
                    .code(UNSUPPORTED_MEDIA_TYPE.value()).message("Media type not supported")
                    .responseModel(new ModelRef("Exception"))
                    .build()
    ),

    NOT_ACCEPTABLE_MESSAGE(
            new ResponseMessageBuilder()
                    .code(NOT_ACCEPTABLE.value()).message("Representation not acceptable")
                    .build()
    );

    private final ResponseMessage message;

    DefaultMessages(final ResponseMessage message) {
        this.message = message;
    }
}
