package com.ffs.algafood.api.exception.model;

import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author francisco
 */
@Getter
@Builder
public class Field {

    private final String name;
    private final String userMessage;
}
