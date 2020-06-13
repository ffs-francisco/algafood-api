package com.ffs.algafood.api.model.response.user;

import com.ffs.algafood.domain.model.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
public class UserResponse implements Serializable {

    private Long id;
    private String name;
    private String email;

    public static UserResponse from(final User user) {
        return new ModelMapper().map(user, UserResponse.class);
    }

    public static List<UserResponse> fromList(Collection<User> users) {
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}
