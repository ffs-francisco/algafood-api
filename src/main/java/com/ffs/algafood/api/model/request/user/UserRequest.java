package com.ffs.algafood.api.model.request.user;

import com.ffs.algafood.domain.model.User;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class UserRequest implements Serializable {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    public void copyPropertiesTo(final User user) {
        new ModelMapper().map(this, user);
    }
}
