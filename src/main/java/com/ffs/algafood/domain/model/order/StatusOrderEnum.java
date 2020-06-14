package com.ffs.algafood.domain.model.order;

/**
 *
 * @author francisco
 */
public enum StatusOrderEnum {

    CREATED("created"),
    CONFIRMED("confirmed"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private final String description;

    private StatusOrderEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
