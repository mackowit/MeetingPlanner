package com.crud.planner.mapper;

import com.crud.planner.domain.Group;
import com.crud.planner.domain.GroupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupMapperTest {

    @Autowired
    private GroupMapper groupMapper;

    @Test
    public void toGroupMapping() {
        //Given
        GroupDto groupDto = new GroupDto(1L, "groupName");

        //When
        Group group = groupMapper.mapToGroup(groupDto);

        //Then
        assertEquals("groupName", group.getName());
    }

    @Test
    public void toDtoMapping() {
        //Given
        Group group = new Group(1L, "groupName");

        //When
        GroupDto groupDto = groupMapper.mapToGroupDto(group);

        //Then
        assertEquals("groupName", groupDto.getName());
    }

    @Test
    public void toDtoListMapping() {
        //Given
        Group group1 = new Group(1L, "groupName1");
        Group group2 = new Group(2L, "groupName2");
        Group group3 = new Group(3L, "groupName3");
        List<Group> groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);
        groupList.add(group3);

        //When
        List<GroupDto> groupDtoList = groupMapper.mapToGroupDtoList(groupList);

        //Then
        assertEquals(3, groupDtoList.size());
    }
}
