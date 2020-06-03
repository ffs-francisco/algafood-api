package com.ffs.algafood.api.model.response.restaurant;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class AddressResponse {

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String district;
    private CityResponse city;
}
