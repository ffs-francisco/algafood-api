package com.ffs.algafood.api.model.response;

import com.ffs.algafood.domain.model.Kitchen;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author francisco
 */
@Getter
@Builder
class KitichenResponse implements Serializable {

    private final Long id;
    private final String name;

    static KitichenResponse from(Kitchen kitchen) {
        return KitichenResponse.builder()
                .id(kitchen.getId())
                .name(kitchen.getName())
                .build();
    }
}
