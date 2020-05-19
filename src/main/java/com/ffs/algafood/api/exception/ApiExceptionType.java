package com.ffs.algafood.api.exception;

import lombok.Getter;

/**
 *
 * @author francisco
 */
@Getter
public enum ApiExceptionType {

    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible Message"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity Not Found"),
    ENTITY_IN_USE("/entity-in-use", "Entity In Use"),
    ERROR_BUSINESS("/error-business", "Business Rule Violation");

    private final String uri;
    private final String title;

    private ApiExceptionType(String uri, String title) {
        this.uri = "https://algafood.com.br" + uri;
        this.title = title;
    }
}
