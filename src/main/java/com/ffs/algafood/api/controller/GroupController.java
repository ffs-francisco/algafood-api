package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.response.group.GroupResponse;
import com.ffs.algafood.domain.service.GroupService;
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
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    @ResponseStatus(OK)
    public List<GroupResponse> listAll() {
        return GroupResponse.fromList(groupService.findAll());
    }

    @GetMapping("/{groupId}")
    @ResponseStatus(OK)
    public GroupResponse getById(@PathVariable final Long groupId) {
        return GroupResponse.from(groupService.findById(groupId));
    }
}
