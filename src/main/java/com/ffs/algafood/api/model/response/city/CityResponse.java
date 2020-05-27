package com.ffs.algafood.api.model.response.city;

import com.ffs.algafood.api.model.response.state.StateResponse;
import com.ffs.algafood.domain.model.City;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

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

    public static List<CityResponse> fromList(List<City> citys) {
        return citys.stream()
                .map(city -> from(city))
                .collect(Collectors.toList());
    }
}
