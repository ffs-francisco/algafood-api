package com.ffs.algafood.api.controller.user;

import com.ffs.algafood.api.model.response.permission.GroupResponse;
import com.ffs.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupConctroller {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(OK)
    public List<GroupResponse> listAll(@PathVariable final Long userId) {
        return GroupResponse.fromList(userService.findById(userId).getGroups());
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(OK)
    public void link(@PathVariable final Long userId, @PathVariable final Long groupId) {
        userService.linkGroup(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(OK)
    public void unlink(@PathVariable final Long userId, @PathVariable final Long groupId) {
        userService.unlinkGroup(userId, groupId);
    }

}
