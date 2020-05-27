package com.ffs.algafood.api.model.request.city;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
