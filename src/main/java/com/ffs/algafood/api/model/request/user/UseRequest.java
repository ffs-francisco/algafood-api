package com.ffs.algafood.api.model.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class UseRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
}
