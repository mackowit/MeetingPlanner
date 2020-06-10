package com.crud.planner.controller;

import com.crud.planner.domain.*;
import com.crud.planner.mapper.LocationMapper;
import com.crud.planner.mapper.MeetingMapper;
import com.crud.planner.service.LocationService;
import com.crud.planner.service.MeetingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetingController.class)
public class MeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetingService meetingService;

    @MockBean
    private MeetingMapper meetingMapper;

    @Test
    public void shouldReturnMeetings() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        User user = new User(1L, "name", "lastname", "email", new Group(1L, "groupName"));
        List<User> participants = new ArrayList<>();
        participants.add(user);
        Meeting meeting = new Meeting(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0),
                location, participants, "Test");
        List<Meeting> meetingList = new ArrayList<>();
        meetingList.add(meeting);
        MeetingDto meetingDto = new MeetingDto(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0 ),
                location, participants, "Test");
        List<MeetingDto> meetingDtoList = new ArrayList<>();
        meetingDtoList.add(meetingDto);

        when(meetingService.getAllMeetings()).thenReturn(meetingList);
        when(meetingMapper.mapToMeetingDtoList(meetingList)).thenReturn(meetingDtoList);

        //When&Then
        mockMvc.perform(get("/v1/planner/meetings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].topic", is("Test meeting")))
                .andExpect(jsonPath("$[0].startDate", is("2020-04-30T12:00:00")))
                .andExpect(jsonPath("$[0].endDate", is("2020-04-30T13:00:00")))
                .andExpect(jsonPath("$[0].location.description", is("description")))
                .andExpect(jsonPath("$[0].location.city", is("city")))
                .andExpect(jsonPath("$[0].location.address", is("address")))
                .andExpect(jsonPath("$[0].participants[0].firstname", is("name")))
                .andExpect(jsonPath("$[0].participants[0].lastname", is("lastname")))
                .andExpect(jsonPath("$[0].participants[0].email", is("email")))
                .andExpect(jsonPath("$[0].participants[0].group.name", is("groupName")))
                .andExpect(jsonPath("$[0].content", is("Test")));
    }

    @Test
    public void shouldDeleteMeeting() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        User user = new User(1L, "name", "lastname", "email", new Group(1L, "groupName"));
        List<User> participants = new ArrayList<>();
        participants.add(user);
        Meeting meeting = new Meeting(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0),
                location, participants, "Test");
        //When&Then
        mockMvc.perform(delete("/v1/planner/meetings/{meetingId}", meeting.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateMeeting() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        User user = new User(1L, "name", "lastname", "email", new Group(1L, "groupName"));
        List<User> participants = new ArrayList<>();
        participants.add(user);
        MeetingDto updatedMeetingDto = new MeetingDto(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0 ),
                location, participants, "Test");

        when(meetingMapper.mapToMeetingDto(meetingService.updateMeeting(meetingMapper.mapToMeeting(updatedMeetingDto)))).thenReturn(updatedMeetingDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String jsonContent = gson.toJson(updatedMeetingDto);
        //When&Then
        mockMvc.perform(put("/v1/planner/meetings").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.topic", is("Test meeting")))
                .andExpect(jsonPath("$.startDate", is("2020-04-30T12:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-04-30T13:00:00")))
                .andExpect(jsonPath("$.location.description", is("description")))
                .andExpect(jsonPath("$.location.city", is("city")))
                .andExpect(jsonPath("$.location.address", is("address")))
                .andExpect(jsonPath("$.participants[0].firstname", is("name")))
                .andExpect(jsonPath("$.participants[0].lastname", is("lastname")))
                .andExpect(jsonPath("$.participants[0].email", is("email")))
                .andExpect(jsonPath("$.participants[0].group.name", is("groupName")))
                .andExpect(jsonPath("$.content", is("Test")));
    }

    @Test
    public void shouldCreateMeeting() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        User user = new User(1L, "name", "lastname", "email", new Group(1L, "groupName"));
        List<User> participants = new ArrayList<>();
        participants.add(user);
        MeetingDto newMeetingDto = new MeetingDto(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0 ),
                location, participants, "Test");

        //when(locationMapper.mapToLocationDto(locationService.saveLocation(locationMapper.mapToLocation(newLocationDto)))).thenReturn(newLocationDto);
        when(meetingMapper.mapToMeetingDto(meetingService.saveMeeting(meetingMapper.mapToMeeting(newMeetingDto)))).thenReturn(newMeetingDto);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String jsonContent = gson.toJson(newMeetingDto);
        //When&Then
        mockMvc.perform(post("/v1/planner/meetings").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.topic", is("Test meeting")))
                .andExpect(jsonPath("$.startDate", is("2020-04-30T12:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-04-30T13:00:00")))
                .andExpect(jsonPath("$.location.description", is("description")))
                .andExpect(jsonPath("$.location.city", is("city")))
                .andExpect(jsonPath("$.location.address", is("address")))
                .andExpect(jsonPath("$.participants[0].firstname", is("name")))
                .andExpect(jsonPath("$.participants[0].lastname", is("lastname")))
                .andExpect(jsonPath("$.participants[0].email", is("email")))
                .andExpect(jsonPath("$.participants[0].group.name", is("groupName")))
                .andExpect(jsonPath("$.content", is("Test")));
    }

    @Test
    public void shouldGetMeetingById() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        User user = new User(1L, "name", "lastname", "email", new Group(1L, "groupName"));
        List<User> participants = new ArrayList<>();
        participants.add(user);
        Meeting meeting = new Meeting(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0),
                location, participants, "Test");
        MeetingDto meetingDto = new MeetingDto(1L, "Test meeting", LocalDateTime.of(2020, 4, 30, 12, 0),
                LocalDateTime.of(2020, 4, 30, 13, 0 ),
                location, participants, "Test");

        when(meetingService.getMeetingById(meeting.getId())).thenReturn(Optional.of(meeting));
        when(meetingMapper.mapToMeetingDto(meeting)).thenReturn(meetingDto);

        //When&Then
        mockMvc.perform(get("/v1/planner/meetings/{meetingId}", meeting.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.topic", is("Test meeting")))
                .andExpect(jsonPath("$.startDate", is("2020-04-30T12:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-04-30T13:00:00")))
                .andExpect(jsonPath("$.location.description", is("description")))
                .andExpect(jsonPath("$.location.city", is("city")))
                .andExpect(jsonPath("$.location.address", is("address")))
                .andExpect(jsonPath("$.participants[0].firstname", is("name")))
                .andExpect(jsonPath("$.participants[0].lastname", is("lastname")))
                .andExpect(jsonPath("$.participants[0].email", is("email")))
                .andExpect(jsonPath("$.participants[0].group.name", is("groupName")))
                .andExpect(jsonPath("$.content", is("Test")));
    }
}
