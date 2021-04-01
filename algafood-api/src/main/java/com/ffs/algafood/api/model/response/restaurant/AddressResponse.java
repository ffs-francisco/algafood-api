package com.ffs.algafood.api.model.response.restaurant;

import com.ffs.algafood.domain.model.Address;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

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

    public static AddressResponse from(final Address address) {
        if (address == null) {
            return null;
        }

        final var mapper = new ModelMapper();
        final var mapping = mapper.createTypeMap(Address.class, AddressResponse.class);
        mapping.<String>addMapping(
                (addressSrc) -> addressSrc.getCity().getState().getName(),
                (addressDest, value) -> addressDest.getCity().setState(value)
        );

        return mapper.map(address, AddressResponse.class);
    }
}
