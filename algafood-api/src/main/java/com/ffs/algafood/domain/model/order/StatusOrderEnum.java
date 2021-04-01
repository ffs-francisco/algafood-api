package com.ffs.algafood.domain.model.order;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author francisco
 */
public enum StatusOrderEnum {

    CREATED("created"),
    CONFIRMED("confirmed", CREATED),
    DELIVERED("delivered", CONFIRMED),
    CANCELED("canceled", CREATED);

    private final String description;
    private final List<StatusOrderEnum> previousStatus;

    StatusOrderEnum(String description, StatusOrderEnum... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public String getDescription() {
        return description;
    }

    public boolean isValidStatusChanging(StatusOrderEnum status) {
        return status.previousStatus.contains(this);
    }
}
