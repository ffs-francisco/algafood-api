package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.response.user.UserResponse;
import com.ffs.algafood.domain.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

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
}
