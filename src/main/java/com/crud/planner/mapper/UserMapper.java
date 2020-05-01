package com.crud.planner.mapper;

import com.crud.planner.domain.User;
import com.crud.planner.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getEmail(),
                userDto.getGroup()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getGroup()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(u.getId(), u.getFirstname(), u.getLastname(), u.getEmail(), u.getGroup()))
                .collect(Collectors.toList());
    }
}
