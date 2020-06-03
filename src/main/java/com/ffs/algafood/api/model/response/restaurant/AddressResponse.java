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
    private String number;
    private String district;
    private String street;
    private String complement;
    private CityResponse city;
}
