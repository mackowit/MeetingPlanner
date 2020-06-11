package com.crud.planner.mapper;

import com.crud.planner.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingMapperTest {

    @Autowired
    private MeetingMapper meetingMapper;

    @Test
    public void toMeetingMapping() {
        //Given
        User user = new User(1L, "firstname", "lastname", "email", new Group(1L, "groupName"));
        Location location = new Location(1L, "description", "city", "address");
        List<User> participants = new ArrayList<>();
        participants.add(user);
        MeetingDto meetingDto = new MeetingDto(1L, "Board meeting", LocalDateTime.of(2020, 4, 30, 12, 0, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0, 0 ),
                location, participants, "Annual Board meeting");

        //When
        Meeting meeting = meetingMapper.mapToMeeting(meetingDto);

        //Then
        assertEquals("from: 30.4.2020 12 to: 30.4.2020 13", meeting.datesToString(meeting.getStartDate(), meeting.getEndDate()));
    }

    @Test
    public void toDtoMapping() {
        //Given
        User user = new User(1L, "firstname", "lastname", "email", new Group(1L, "groupName"));
        Location location = new Location(1L, "description", "city", "address");
        List<User> participants = new ArrayList<>();
        participants.add(user);
        Meeting meeting = new Meeting(1L, "Board meeting", LocalDateTime.of(2020, 4, 30, 12, 0, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0, 0 ),
                location, participants, "Annual Board meeting");

        //When
        MeetingDto meetingDto = meetingMapper.mapToMeetingDto(meeting);

        //Then
        assertEquals(1, meetingDto.getParticipants().size());
    }

    @Test
    public void toDtoListMapping() {
        //Given
        User user = new User(1L, "firstname", "lastname", "email", new Group(1L, "groupName"));
        Location location = new Location(1L, "description", "city", "address");
        List<User> participants = new ArrayList<>();
        participants.add(user);
        Meeting meeting = new Meeting(1L, "Board meeting", LocalDateTime.of(2020, 4, 30, 12, 0, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0, 0 ),
                location, participants, "Annual Board meeting");
        List<Meeting> meetingList = new ArrayList<>();
        meetingList.add(meeting);

        //When
        List<MeetingDto> meetingDtoList = meetingMapper.mapToMeetingDtoList(meetingList);

        //Then
        assertEquals("description", meetingDtoList.get(0).getLocation().getDescription());
    }
}
