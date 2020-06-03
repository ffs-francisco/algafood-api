package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.group.GroupRequest;
import com.ffs.algafood.api.model.response.group.GroupResponse;
import com.ffs.algafood.domain.service.GroupService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

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

    @PostMapping
    @ResponseStatus(CREATED)
    public GroupResponse add(@RequestBody @Valid final GroupRequest groupRequest) {
        return GroupResponse.from(groupService.save(groupRequest.toModel()));
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(OK)
    public GroupResponse update(
            @PathVariable final Long groupId,
            @RequestBody @Valid final GroupRequest groupRequest
    ) {
        final var groupSaved = groupService.findById(groupId);

        groupRequest.copyPropertiesTo(groupSaved);
        return GroupResponse.from(groupService.save(groupSaved));
    }
}
