package com.ffs.algafood.api.model.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class PasswordRequest {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
