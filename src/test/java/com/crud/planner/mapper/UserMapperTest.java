package com.crud.planner.mapper;

import com.crud.planner.domain.Group;
import com.crud.planner.domain.User;
import com.crud.planner.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Max;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void toUserMapping() {
        //Given
        UserDto userDto = new UserDto(1L, "firstname", "lastname", "email", new Group(1L, "groupName"));

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals("firstname", user.getFirstname());
    }

    @Test
    public void toDtoMapping() {
        //Given
        User user = new User(1L, "firstname", "lastname", "email", new Group(1L, "groupName"));

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals("groupName", userDto.getGroup().getName());
    }

    @Test
    public void toDtoListMapping() {
        //Given
        User user = new User(1L, "firstname", "lastname", "email", new Group(1L, "groupName"));
        List<User> userList = new ArrayList<>();
        userList.add(user);

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertEquals("email", userDtoList.get(0).getEmail());
    }
}
