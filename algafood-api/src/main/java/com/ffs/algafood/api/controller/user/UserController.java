package com.ffs.algafood.api.controller.user;

import com.ffs.algafood.api.model.request.user.PasswordRequest;
import com.ffs.algafood.api.model.request.user.UserRequest;
import com.ffs.algafood.api.model.request.user.UserWithPasswordRequest;
import com.ffs.algafood.api.model.response.user.UserResponse;
import com.ffs.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserResponse> listAll() {
        return UserResponse.fromList(userService.findAll());
    }

    @GetMapping("/{userId}")
    @ResponseStatus(OK)
    public UserResponse getById(@PathVariable final Long userId) {
        return UserResponse.from(userService.findById(userId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserResponse add(@RequestBody @Valid UserWithPasswordRequest userRequest) {
        var user = userRequest.toModel();
        return UserResponse.from(userService.save(user));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(CREATED)
    public UserResponse update(
            @PathVariable final Long userId,
            @RequestBody @Valid UserRequest userRequest
    ) {
        var userSaved = userService.findById(userId);

        userRequest.copyPropertiesTo(userSaved);
        return UserResponse.from(userService.save(userSaved));
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(NO_CONTENT)
    public void updatePassword(
            @PathVariable final Long userId,
            @RequestBody @Valid PasswordRequest passwordRequest
    ) {
        userService.updatePassword(userId, passwordRequest.getCurrentPassword(), passwordRequest.getNewPassword());
    }
}
