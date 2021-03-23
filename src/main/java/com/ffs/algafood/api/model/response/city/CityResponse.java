package com.ffs.algafood.api.model.response.city;

import com.ffs.algafood.api.model.response.state.StateResponse;
import com.ffs.algafood.domain.model.City;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author francisco
 */
@ApiModel(value = "City", description = "Representation of a city")
@Getter
@Setter
public class CityResponse implements Serializable {

    @ApiModelProperty(value = "ID of a city", example = "1")
    private Long id;

    @ApiModelProperty(value = "Name of a city", example = "São Luis")
    private String name;

    @ApiModelProperty(value = "State of a city")
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
