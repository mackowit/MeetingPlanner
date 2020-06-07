package com.crud.planner.scheduler;

import com.crud.planner.domain.Mail;
import com.crud.planner.domain.Meeting;
import com.crud.planner.domain.User;
import com.crud.planner.repository.MeetingRepository;
import com.crud.planner.repository.UserRepository;
import com.crud.planner.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String SUBJECT = "Meeting planner: your everyday meetings reminder";

    //@Scheduled(fixedDelay = 300000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        List<User> usersList = userRepository.findAll();
        for (User user : usersList) {
            List<Meeting> usersMeetings = getUsersMeetingsToBe(user);

            mailService.send(new Mail(
                    user.getEmail(),
                    SUBJECT,
                    "Everyday information from your Meeting Planner Application about meetings to come that you've been invited for: \n"
                            + prepareMeetingsInformation(usersMeetings)
                            + "\n\n\nYour Meeting Planner Application"
            ));
        }
    }

    public List<Meeting> getUsersMeetingsToBe(User user) {
        List<Meeting> usersMeetingList = new ArrayList<>();
        List<Meeting> meetingsList = meetingRepository.findAll();
        List<Meeting> meetingsListToBe = meetingsList.stream()
                .filter(m -> m.getStartDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        for (Meeting meeting : meetingsListToBe) {
            List<User> meetingParticipantsList = meeting.getParticipants();
            List<User> matchedUsersInMeetings = meetingParticipantsList.stream()
                    .filter(u -> u.getId().equals(user.getId()))
                    .collect(Collectors.toList());
            if (matchedUsersInMeetings.size() > 0) usersMeetingList.add(meeting);
        }
        System.out.println(usersMeetingList);
        return usersMeetingList;
    }

    public String prepareMeetingsInformation(List<Meeting> meetingList) {
        String meetingsInformation = "";
        for(Meeting meeting : meetingList) {
            meetingsInformation += meeting.toString();
        }
        return meetingsInformation;
    }
}
