package com.ffs.algafood.api.model.request.city;

import com.ffs.algafood.domain.model.City;
import com.ffs.algafood.domain.model.State;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class CityRequest {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateIdRequest state;

    public City toModel() {
        return new ModelMapper().map(this, City.class);
    }

    public void copyPropertiesTo(City city) {
        /**
         * To avoid the a exception when trying to change the entity ID.
         *
         * org.hibernate.HibernateException: identifier of an instance of
         * com.domain.Entity was altered from 1 to 2
         */
        city.setState(new State());

        new ModelMapper().map(this, city);
    }
}
