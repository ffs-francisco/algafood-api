package com.ffs.algafood.api.model.request.state;

import com.ffs.algafood.domain.model.State;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class StateRequest implements Serializable {

    @NotBlank
    private String name;

    public State toModel() {
        return new ModelMapper().map(this, State.class);
    }

    public void copyPropertiesTo(State state) {
        new ModelMapper().map(this, state);
    }
}
