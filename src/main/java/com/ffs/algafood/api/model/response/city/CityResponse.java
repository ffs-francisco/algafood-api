package com.ffs.algafood.api.model.response.city;

import com.ffs.algafood.api.model.response.state.StateResponse;
import com.ffs.algafood.domain.model.City;
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
public class CityResponse implements Serializable {

    private Long id;
    private String name;
    private StateResponse state;

    public static CityResponse from(City city) {
        return new ModelMapper().map(city, CityResponse.class);
    }

    public static List<CityResponse> fromList(List<City> cities) {
        return cities.stream()
                .map(CityResponse::from)
                .collect(Collectors.toList());
    }
}
