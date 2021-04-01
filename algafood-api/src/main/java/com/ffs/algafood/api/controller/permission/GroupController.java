package com.ffs.algafood.api.controller.permission;

import com.ffs.algafood.api.model.request.group.GroupRequest;
import com.ffs.algafood.api.model.response.permission.GroupResponse;
import com.ffs.algafood.domain.service.permission.GroupService;
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

    @DeleteMapping("/{groupId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final Long groupId) {
        groupService.delete(groupId);
    }
}
