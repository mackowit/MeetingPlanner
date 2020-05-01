package com.crud.planner.controller;

import com.crud.planner.domain.LocationDto;
import com.crud.planner.exception.LocationNotFoundException;
import com.crud.planner.mapper.LocationMapper;
import com.crud.planner.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/planner")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationMapper locationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/location")
    public List<LocationDto> getLocations() {
        return locationMapper.mapToLocationDtoList(locationService.getAllLocations());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/location/{locationId}")
    public LocationDto getLocation(@PathVariable Long locationId) throws LocationNotFoundException {
        return locationMapper.mapToLocationDto(locationService.getLocationById(locationId).orElseThrow(LocationNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/location/{locationId}")
    public void deleteLocation(@PathVariable Long locationId) {
        locationService.deleteLocation(locationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/location")
    public LocationDto updateLocation(@RequestBody LocationDto locationDto) {
        return locationMapper.mapToLocationDto(locationService.updateLocation(locationMapper.mapToLocation(locationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/location", consumes = APPLICATION_JSON_VALUE)
    public LocationDto addLocation(@RequestBody LocationDto locationDto) {
        return locationMapper.mapToLocationDto(locationService.saveLocation(locationMapper.mapToLocation(locationDto)));
    }
}
