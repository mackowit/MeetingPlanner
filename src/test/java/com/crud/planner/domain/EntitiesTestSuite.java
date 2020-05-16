package com.crud.planner.domain;

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
        User user1 = new User(null, "Marta", "Witecka", "macka81@o2.pl", financialGroup);
        User user2 = new User(null, "Ebi", "Cocker", "guebida8081@gmail.com", controllingGroup);
        Meeting meeting = new Meeting(null, LocalDateTime.of(2020, 4, 30, 12, 0, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0, 0 ),
                location, null);
        meeting.getParticipants().add(user1);
        meeting.getParticipants().add(user2);
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