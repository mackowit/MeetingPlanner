package com.crud.planner.repository;

import com.crud.planner.domain.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    @Override
    List<Meeting> findAll();

    @Override
    Meeting save(Meeting meeting);

    @Override
    Optional<Meeting> findById(Long id);

    @Override
    void deleteById(Long id);
}
