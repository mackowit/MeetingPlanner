package com.crud.planner.domain;

import com.crud.planner.controller.MeetingController;
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
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetUserMeetingsTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    MeetingController meetingController;

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void getUserMeetings() {
        Long userId = 69L;

        List<Meeting> usersMeetingList = new ArrayList<>();
        List<Meeting> meetingsList = meetingRepository.findAll();
        usersMeetingList = userRepository.findById(userId).get().getMeetings();
        System.out.println(usersMeetingList);
        /*meetingsList.stream()
                .map(e -> e.getParticipants().stream()
                        .filter(u -> u.getId().equals(userId))
                        .
                )*//*

        List<Meeting> meetingsListToBe = meetingsList.stream()
                .filter(m -> m.getStartDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        for (Meeting meeting : meetingsListToBe) {
            List<User> meetingParticipantsList = meeting.getParticipants();
            List<User> matchedUsersInMeetings = meetingParticipantsList.stream()
                    .filter(u -> u.getId().equals(userId))
                    .collect(Collectors.toList());
            if (matchedUsersInMeetings.size() > 0) usersMeetingList.add(meeting);
        }
        System.out.println(usersMeetingList);*/

    }
}
