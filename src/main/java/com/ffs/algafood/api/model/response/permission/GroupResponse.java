package com.ffs.algafood.api.model.response.permission;

import com.ffs.algafood.domain.model.permission.Group;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class GroupResponse {

    private Long id;
    private String name;

    public static GroupResponse from(Group group) {
        return new ModelMapper().map(group, GroupResponse.class);
    }

    public static List<GroupResponse> fromList(Collection<Group> groups) {
        return groups.stream()
                .map(GroupResponse::from)
                .collect(Collectors.toList());
    }
}
