package com.crud.planner.domain;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Location location;
    private List<User> participants;

    public MeetingDto(Long id, LocalDateTime startDate, LocalDateTime endDate, Location location, List<User> participants) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Location getLocation() {
        return location;
    }

    public List<User> getParticipants() {
        return participants;
    }
}
