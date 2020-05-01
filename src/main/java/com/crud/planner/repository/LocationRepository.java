package com.crud.planner.repository;

import com.crud.planner.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    @Override
    List<Location> findAll();

    @Override
    Location save(Location location);

    @Override
    Optional<Location> findById(Long id);

    @Override
    void deleteById(Long id);
}
