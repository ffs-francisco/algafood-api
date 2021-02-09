package com.ffs.algafood.api.model.request.user;

import com.ffs.algafood.domain.model.User;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class UserWithPasswordRequest extends UserRequest {

    @NotBlank
    private String password;

    public User toModel() {
        return new ModelMapper().map(this, User.class);
    }
}
