package com.crud.planner.mapper;

import com.crud.planner.domain.Group;
import com.crud.planner.domain.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(
                groupDto.getId(),
                groupDto.getName()
        );
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getName()
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }
}
