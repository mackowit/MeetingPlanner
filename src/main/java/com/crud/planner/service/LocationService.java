package com.crud.planner.service;

import com.crud.planner.domain.Location;
import com.crud.planner.exception.LocationNotFoundException;
import com.crud.planner.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location saveLocation(final Location location) {
        return locationRepository.save(location);
    }

    public Optional<Location> getLocationById(final Long id) {
        return locationRepository.findById(id);
    }

    public void deleteLocation(final Long id) {
        locationRepository.deleteById(id);
    }

    public Location updateLocation(final Location locationAfterChange) {
        if (locationRepository.existsById(locationAfterChange.getId())) {
            return locationRepository.save(locationAfterChange);
        } else {
            throw new LocationNotFoundException();
        }
    }
}
