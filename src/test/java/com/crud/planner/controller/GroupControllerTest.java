package com.crud.planner.controller;

import com.crud.planner.domain.Group;
import com.crud.planner.domain.GroupDto;
import com.crud.planner.mapper.GroupMapper;
import com.crud.planner.service.GroupService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupMapper groupMapper;

    @Test
    public void shouldReturnGroups() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        GroupDto groupDto = new GroupDto(1L, "Test group");
        List<GroupDto> groupDtoList = new ArrayList<>();
        groupDtoList.add(groupDto);

        when(groupService.getAllGroups()).thenReturn(groupList);
        when(groupMapper.mapToGroupDtoList(groupList)).thenReturn(groupDtoList);

        //When&Then
        mockMvc.perform(get("/v1/planner/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test group")));
    }

    @Test
    public void shouldDeleteGroup() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        //When&Then
        mockMvc.perform(delete("/v1/planner/groups/{groupId}", group.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateGroup() throws Exception {
        //Given
        GroupDto updatedGroupDto = new GroupDto(1L, "Updated test group");

        when(groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToGroup(updatedGroupDto)))).thenReturn(updatedGroupDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedGroupDto);
        //When&Then
        mockMvc.perform(put("/v1/planner/groups").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated test group")));
    }

    @Test
    public void shouldCreateGroup() throws Exception {
        //Given
        GroupDto newGroupDto = new GroupDto(1L, "New test group");

        when(groupMapper.mapToGroupDto(groupService.saveGroup(groupMapper.mapToGroup(newGroupDto)))).thenReturn(newGroupDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(newGroupDto);
        //When&Then
        mockMvc.perform(post("/v1/planner/groups").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("New test group")));
    }

    @Test
    public void shouldGetGroupById() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        GroupDto groupDto = new GroupDto(1L, "Test group");

        when(groupService.getGroupById(group.getId())).thenReturn(Optional.of(group));
        when(groupMapper.mapToGroupDto(group)).thenReturn(groupDto);

        //When&Then
        mockMvc.perform(get("/v1/planner/groups/{groupId}", group.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                //.param("taskId", task.getId().toString()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test group")));
    }
}
