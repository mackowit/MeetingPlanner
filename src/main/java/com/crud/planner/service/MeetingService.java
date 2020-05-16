package com.crud.planner.service;

import com.crud.planner.domain.Meeting;
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

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Meeting saveMeeting(final Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Optional<Meeting> getMeetingById(final Long id) {
        return meetingRepository.findById(id);
    }

    public void deleteMeeting(final Long id) {
        meetingRepository.deleteById(id);
    }

    public Meeting updateMeeting(final Meeting meetingAfterChange) {
        if (meetingRepository.existsById(meetingAfterChange.getId())) {
            return meetingRepository.save(meetingAfterChange);
        } else {
            throw new MeetingNotFoundException();
        }
    }
}
