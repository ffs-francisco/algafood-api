package com.ffs.algafood.api.exception.model;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

/**
 * @author francisco
 */
@ApiModel("Error")
@Getter
@Builder
public class ObjectError {

    private final String name;
    private final String userMessage;
}
