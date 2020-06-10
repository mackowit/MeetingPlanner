package com.crud.planner.controller;

import com.crud.planner.domain.UserDto;
import com.crud.planner.exception.UserNotFoundException;
import com.crud.planner.mapper.UserMapper;
import com.crud.planner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/planner")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getuser(@PathVariable Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser(@RequestBody UserDto UserDto) {
        return userMapper.mapToUserDto(userService.updateUser(userMapper.mapToUser(UserDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public UserDto addUser(@RequestBody UserDto UserDto) {
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(UserDto)));
    }
}
