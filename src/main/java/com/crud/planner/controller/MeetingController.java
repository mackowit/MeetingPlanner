package com.crud.planner.controller;

import com.crud.planner.domain.MeetingDto;
import com.crud.planner.exception.MeetingNotFoundException;
import com.crud.planner.mapper.MeetingMapper;
import com.crud.planner.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/planner")
public class MeetingController {
    
    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingMapper meetingMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/meetings")
    public List<MeetingDto> getMeetings() {
        return meetingMapper.mapToMeetingDtoList(meetingService.getAllMeetings());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/meetings/{meetingId}")
    public MeetingDto getMeeting(@PathVariable Long meetingId) throws MeetingNotFoundException {
        return meetingMapper.mapToMeetingDto(meetingService.getMeetingById(meetingId).orElseThrow(MeetingNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/meetings/{meetingId}")
    public void deleteMeeting(@PathVariable Long meetingId) {
        meetingService.deleteMeeting(meetingId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/meetings")
    public MeetingDto updateMeeting(@RequestBody MeetingDto meetingDto) {
        return meetingMapper.mapToMeetingDto(meetingService.updateMeeting(meetingMapper.mapToMeeting(meetingDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/meetings", consumes = APPLICATION_JSON_VALUE)
    public MeetingDto addMeeting(@RequestBody MeetingDto meetingDto) {
        return meetingMapper.mapToMeetingDto(meetingService.saveMeeting(meetingMapper.mapToMeeting(meetingDto)));
    }
}
