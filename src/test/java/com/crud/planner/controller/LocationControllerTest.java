package com.crud.planner.controller;

import com.crud.planner.domain.Location;
import com.crud.planner.domain.LocationDto;
import com.crud.planner.mapper.LocationMapper;
import com.crud.planner.service.LocationService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(LocationController.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @MockBean
    private LocationMapper locationMapper;

    @Test
    public void shouldReturnLocations() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        List<Location> locationList = new ArrayList<>();
        locationList.add(location);
        LocationDto locationDto = new LocationDto(1L, "description", "city", "address");
        List<LocationDto> locationDtoList = new ArrayList<>();
        locationDtoList.add(locationDto);

        when(locationService.getAllLocations()).thenReturn(locationList);
        when(locationMapper.mapToLocationDtoList(locationList)).thenReturn(locationDtoList);

        //When&Then
        mockMvc.perform(get("/v1/planner/location").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("description")))
                .andExpect(jsonPath("$[0].city", is("city")))
                .andExpect(jsonPath("$[0].address", is("address")));
    }

    @Test
    public void shouldDeleteLocation() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        //When&Then
        mockMvc.perform(delete("/v1/planner/location/{locationId}", location.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateLocation() throws Exception {
        //Given
        LocationDto updatedLocationDto = new LocationDto(1L, "description", "city", "address");

        when(locationMapper.mapToLocationDto(locationService.updateLocation(locationMapper.mapToLocation(updatedLocationDto)))).thenReturn(updatedLocationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedLocationDto);
        //When&Then
        mockMvc.perform(put("/v1/planner/location").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.address", is("address")));
    }

    @Test
    public void shouldCreateLocation() throws Exception {
        //Given
        LocationDto newLocationDto = new LocationDto(1L, "description", "city", "address");

        when(locationMapper.mapToLocationDto(locationService.saveLocation(locationMapper.mapToLocation(newLocationDto)))).thenReturn(newLocationDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(newLocationDto);
        //When&Then
        mockMvc.perform(post("/v1/planner/location").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.address", is("address")));
    }

    @Test
    public void shouldGetLocationById() throws Exception {
        //Given
        Location location = new Location(1L, "description", "city", "address");
        LocationDto locationDto = new LocationDto(1L, "description", "city", "address");

        when(locationService.getLocationById(location.getId())).thenReturn(Optional.of(location));
        when(locationMapper.mapToLocationDto(location)).thenReturn(locationDto);

        //When&Then
        mockMvc.perform(get("/v1/planner/location/{locationId}", location.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.address", is("address")));
    }
}
