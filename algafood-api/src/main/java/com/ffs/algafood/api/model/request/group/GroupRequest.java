package com.ffs.algafood.api.model.request.group;

import com.ffs.algafood.domain.model.permission.Group;
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
public class GroupRequest {

    @NotBlank
    private String name;

    public Group toModel() {
        return new ModelMapper().map(this, Group.class);
    }

    public void copyPropertiesTo(final Group group) {
        new ModelMapper().map(this, group);
    }
}
