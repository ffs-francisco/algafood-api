package com.ffs.algafood.api.model.response.state;

import com.ffs.algafood.domain.model.State;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class StateResponse implements Serializable {

    private Long id;
    private String name;

    public static StateResponse from(State state) {
        return new ModelMapper().map(state, StateResponse.class);
    }

    public static List<StateResponse> fromList(List<State> states) {
        return states.stream()
                .map(StateResponse::from)
                .collect(Collectors.toList());
    }
}
