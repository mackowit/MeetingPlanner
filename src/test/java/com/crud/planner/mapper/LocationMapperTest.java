package com.crud.planner.mapper;

import com.crud.planner.domain.Location;
import com.crud.planner.domain.LocationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationMapperTest {

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void toLocationMapping() {
        //Given
        LocationDto locationDto = new LocationDto(1L, "description", "city", "address");

        //When
        Location location = locationMapper.mapToLocation(locationDto);

        //Then
        assertEquals("description", location.getDescription());
    }

    @Test
    public void toDtoMapping() {
        //Given
        Location location = new Location(1L, "description", "city", "address");

        //When
        LocationDto locationDto = locationMapper.mapToLocationDto(location);

        //Then
        assertEquals("city", locationDto.getCity());
    }

    @Test
    public void toDtoListMapping() {
        //Given
        Location location1 = new Location(1L, "description1", "city1", "address1");
        Location location2 = new Location(2L, "description2", "city2", "address2");
        Location location3 = new Location(3L, "description3", "city3", "address3");
        List<Location> locationList = new ArrayList<>();
        locationList.add(location1);
        locationList.add(location2);
        locationList.add(location3);

        //When
        List<LocationDto> locationDtoList = locationMapper.mapToLocationDtoList(locationList);

        //Then
        assertEquals("address2", locationDtoList.get(1).getAddress());
    }
}
