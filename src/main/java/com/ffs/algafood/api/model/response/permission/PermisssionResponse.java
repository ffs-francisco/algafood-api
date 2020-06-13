package com.ffs.algafood.api.model.response.permission;

import com.ffs.algafood.domain.model.permission.Permission;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class PermisssionResponse {

    private Long id;
    private String name;
    private String description;

    public static PermisssionResponse from(final Permission permission) {
        return new ModelMapper().map(permission, PermisssionResponse.class);
    }

    public static List<PermisssionResponse> fromList(Set<Permission> permissions) {
        return permissions.stream()
                .map(PermisssionResponse::from)
                .collect(Collectors.toList());
    }

}
