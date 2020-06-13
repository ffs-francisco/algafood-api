package com.ffs.algafood.api.controller.permission;

import com.ffs.algafood.api.model.response.permission.PermisssionResponse;
import com.ffs.algafood.domain.service.permission.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/groups/{groupId}/permissions")
public class GroupPermissionController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    @ResponseStatus(OK)
    public List<PermisssionResponse> listAll(@PathVariable final Long groupId) {
        return PermisssionResponse.fromList(groupService.findById(groupId).getPermissions());
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(NO_CONTENT)
    public void link(@PathVariable final Long groupId, @PathVariable final Long permissionId) {
        groupService.linkPermission(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(NO_CONTENT)
    public void unlink(@PathVariable final Long groupId, @PathVariable final Long permissionId) {
        groupService.unlinkPermission(groupId, permissionId);
    }

}
