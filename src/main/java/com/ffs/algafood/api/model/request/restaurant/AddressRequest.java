package com.ffs.algafood.api.model.request.restaurant;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author francisco
 */
@Getter
@Setter
class AddressRequest {

    @NotBlank
    private String cep;

    @NotBlank
    private String district;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @Valid
    @NotNull
    private CityIdRequest city;
}
