package com.crud.planner.service;

import com.crud.planner.domain.Mail;
import com.crud.planner.domain.Meeting;
import com.crud.planner.domain.User;
import com.crud.planner.exception.MeetingNotFoundException;
import com.crud.planner.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MailService mailService;

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Meeting saveMeeting(final Meeting meeting) {
        for (int i = 0; i < meeting.getParticipants().size(); i++) {
            mailService.send(new Mail(
                    meeting.getParticipants().get(i).getEmail(),
                    "Meeting planner: you have been invited for a meeting",
                    "You have been invited for a meeting: \n " +
                            "Meeting subject: " + meeting.getTopic() + "\n" +
                            "Duration: " + meeting.getStartDate() + " - " + meeting.getEndDate() + "\n" +
                            "Location: " + meeting.getLocation() + "\n" +
                            "Meeting informations: " + meeting.getContent()
            ));
        }
            return meetingRepository.save(meeting);
    }

    public Optional<Meeting> getMeetingById(final Long id) {
        return meetingRepository.findById(id);
    }

    public void deleteMeeting(final Long id) {
        Meeting meeting = getMeetingById(id).orElse(null);
        for (int i = 0; i < meeting.getParticipants().size(); i++) {
            mailService.send(new Mail(
                    meeting.getParticipants().get(i).getEmail(),
                        "Meeting planner: your meeting has been canceled.",
                        "Your meeting has been canceled. Meeting details: \n " +
                                "Meeting subject: " + meeting.getTopic() + "\n" +
                                "Duration: " + meeting.getStartDate() + " - " + meeting.getEndDate() + "\n" +
                                "Location: " + meeting.getLocation() + "\n" +
                                "Meeting informations: " + meeting.getContent()
                ));
            }
            meetingRepository.deleteById(id);
    }

    public Meeting updateMeeting(final Meeting meetingAfterChange) {
        if (meetingRepository.existsById(meetingAfterChange.getId())) {
            for (int i = 0; i < meetingAfterChange.getParticipants().size(); i++) {
                mailService.send(new Mail(
                        meetingAfterChange.getParticipants().get(i).getEmail(),
                        "Meeting planner: your meeting has been changed.",
                        "Your meeting has been changed. New meeting details: \n " +
                                "Meeting subject: " + meetingAfterChange.getTopic() + "\n" +
                                "Duration: " + meetingAfterChange.getStartDate() + " - " + meetingAfterChange.getEndDate() + "\n" +
                                "Location: " + meetingAfterChange.getLocation() + "\n" +
                                "Meeting informations: " + meetingAfterChange.getContent() + "\n" +
                                "New meeting participants list:\n" + meetingParticipantsListing(meetingAfterChange.getParticipants())
                ));
            }
            return meetingRepository.save(meetingAfterChange);
        } else {
            throw new MeetingNotFoundException();
        }
    }

    public String meetingParticipantsListing(List<User> meetingParticipantsList) {
        String participantsListing = "";
        for (User user : meetingParticipantsList) {
            participantsListing += user.toString();
        }
        return participantsListing;
    }
}
