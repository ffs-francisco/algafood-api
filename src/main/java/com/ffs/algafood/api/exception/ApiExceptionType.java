package com.ffs.algafood.api.exception;

import lombok.Getter;

/**
 *
 * @author francisco
 */
@Getter
public enum ApiExceptionType {

    INVALID_PARAMETER("/invalid-path-parameter", "Invalid Path Parameter."),
    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible Message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource Not Found"),
    ENTITY_IN_USE("/entity-in-use", "Entity In Use"),
    ERROR_BUSINESS("/error-business", "Business Rule Violation"),
    INTERNAL_SYSTEM_ERROR("/internal-system-error", "Internal System Error");

    private final String uri;
    private final String title;

    private ApiExceptionType(String uri, String title) {
        this.uri = "https://algafood.com.br" + uri;
        this.title = title;
    }
}
