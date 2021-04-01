package com.ffs.algafood.api.model.request.city;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author francisco
 */
@Getter
@Setter
class StateIdRequest implements Serializable {

    @NotNull
    private Long id;
}
