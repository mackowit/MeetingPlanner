package com.crud.planner.controller;

import com.crud.planner.domain.Group;
import com.crud.planner.domain.User;
import com.crud.planner.domain.UserDto;
import com.crud.planner.mapper.UserMapper;
import com.crud.planner.service.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldReturnUsers() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        User user = new User(1L, "name", "lastname", "email", group);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        UserDto userDto = new UserDto(1L, "name", "lastname", "email", group);
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);

        when(userService.getAllUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When&Then
        mockMvc.perform(get("/v1/planner/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("name")))
                .andExpect(jsonPath("$[0].lastname", is("lastname")))
                .andExpect(jsonPath("$[0].email", is("email")))
                .andExpect(jsonPath("$[0].group.name", is("Test")));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        User user = new User(1L, "name", "lastname", "email", group);
        //When&Then
        mockMvc.perform(delete("/v1/planner/users/{userId}", user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        UserDto updatedUserDto = new UserDto(1L, "name", "lastname", "email", group);

        when(userMapper.mapToUserDto(userService.updateUser(userMapper.mapToUser(updatedUserDto)))).thenReturn(updatedUserDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedUserDto);
        //When&Then
        mockMvc.perform(put("/v1/planner/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("name")))
                .andExpect(jsonPath("$.lastname", is("lastname")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.group.name", is("Test")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        UserDto newUserDto = new UserDto(1L, "name", "lastname", "email", group);

        when(userMapper.mapToUserDto(userService.updateUser(userMapper.mapToUser(newUserDto)))).thenReturn(newUserDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(newUserDto);
        //When&Then
        mockMvc.perform(post("/v1/planner/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("name")))
                .andExpect(jsonPath("$.lastname", is("lastname")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.group.name", is("Test")));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        //Given
        Group group = new Group(1L, "Test");
        User user = new User(1L, "name", "lastname", "email", group);
        UserDto userDto = new UserDto(1L, "name", "lastname", "email", group);

        when(userService.getUserById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When&Then
        mockMvc.perform(get("/v1/planner/users/{userId}", user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("name")))
                .andExpect(jsonPath("$.lastname", is("lastname")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.group.name", is("Test")));
    }
}
