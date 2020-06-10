package com.crud.planner.domain;

import com.crud.planner.domain.Group;
import com.crud.planner.domain.Location;
import com.crud.planner.domain.Meeting;
import com.crud.planner.domain.User;
import com.crud.planner.repository.GroupRepository;
import com.crud.planner.repository.LocationRepository;
import com.crud.planner.repository.MeetingRepository;
import com.crud.planner.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntitiesTestSuite {
    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Test
    public void testMeetingEntityWithLocationUsersAndGroups() {
        //Given
        Location location = new Location(null, "Location1", "Cracow", "Lubicz 24");
        Group controllingGroup = new Group(null, "Controlling Division");
        Group financialGroup = new Group(null, "Financial Division");
        User user1 = new User(null, "Name1", "Lastname1", "email1@o2.pl", financialGroup);
        User user2 = new User(null, "Name2", "Lastname2", "email2@gmail.com", controllingGroup);
        List<User> participants = new ArrayList<>();
        participants.add(user1);
        participants.add(user2);
        Meeting meeting = new Meeting(null, "Board meeting", LocalDateTime.of(2020, 4, 30, 12, 0, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0, 0 ),
                location, participants, "Annual Board meeting");
        //When
        locationRepository.save(location);
        Long locationId = location.getId();
        groupRepository.save(financialGroup);
        Long finGroupId = financialGroup.getId();
        groupRepository.save(controllingGroup);
        Long conGroupId = controllingGroup.getId();
        userRepository.save(user1);
        Long user1Id = user1.getId();
        userRepository.save(user2);
        Long user2Id = user2.getId();
        meetingRepository.save(meeting);
        Long meetingId = meeting.getId();
        //Then
        meetingRepository.deleteById(meetingId);
        locationRepository.deleteById(locationId);
        userRepository.deleteById(user1Id);
        userRepository.deleteById(user2Id);
        groupRepository.deleteById(finGroupId);
        groupRepository.deleteById(conGroupId);
    }
}