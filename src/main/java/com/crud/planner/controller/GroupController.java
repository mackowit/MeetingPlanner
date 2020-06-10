package com.crud.planner.controller;

import com.crud.planner.domain.GroupDto;
import com.crud.planner.exception.GroupNotFoundException;
import com.crud.planner.mapper.GroupMapper;
import com.crud.planner.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/planner")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/groups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupService.getGroupById(groupId).orElseThrow(GroupNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/groups/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/groups")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToGroup(groupDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/groups", consumes = APPLICATION_JSON_VALUE)
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }
}
