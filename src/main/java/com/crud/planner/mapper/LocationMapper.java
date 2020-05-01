package com.crud.planner.mapper;

import com.crud.planner.domain.Location;
import com.crud.planner.domain.LocationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    public Location mapToLocation(final LocationDto locationDto) {
        return new Location(
                locationDto.getId(),
                locationDto.getDescription(),
                locationDto.getCity(),
                locationDto.getAddress()
        );
    }

    public LocationDto mapToLocationDto(final Location location) {
        return new LocationDto(
                location.getId(),
                location.getDescription(),
                location.getCity(),
                location.getAddress()
        );
    }

    public List<LocationDto> mapToLocationDtoList(final List<Location> locationList) {
        return locationList.stream()
                .map(l -> new LocationDto(l.getId(), l.getDescription(), l.getCity(), l.getAddress()))
                .collect(Collectors.toList());
    }
}
