package com.crud.planner.mapper;

import com.crud.planner.domain.Meeting;
import com.crud.planner.domain.MeetingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingMapper {
    public Meeting mapToMeeting(final MeetingDto meetingDto) {
        return new Meeting(
                meetingDto.getId(),
                meetingDto.getTopic(),
                meetingDto.getStartDate(),
                meetingDto.getEndDate(),
                meetingDto.getLocation(),
                meetingDto.getParticipants(),
                meetingDto.getContent()
        );
    }

    public MeetingDto mapToMeetingDto(final Meeting meeting) {
        return new MeetingDto(
                meeting.getId(),
                meeting.getTopic(),
                meeting.getStartDate(),
                meeting.getEndDate(),
                meeting.getLocation(),
                meeting.getParticipants(),
                meeting.getContent()
        );
    }

    public List<MeetingDto> mapToMeetingDtoList(final List<Meeting> meetingList) {
        return meetingList.stream()
                .map(m -> new MeetingDto(m.getId(), m.getTopic(), m.getStartDate(), m.getEndDate(), m.getLocation(), m.getParticipants(), m.getContent()))
                .collect(Collectors.toList());
    }
}
