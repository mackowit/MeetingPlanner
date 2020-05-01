package com.crud.planner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @ManyToOne
    private Location location;

    @ManyToOne
    @NotNull
    private User meetingOwner;

    @ManyToMany
    private List<User> participants;

    public Meeting() {
    }

    public Meeting(Long id, LocalDateTime startDate, LocalDateTime endDate, Location location, User meetingOwner, List<User> participants) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.meetingOwner = meetingOwner;
        this.participants = new ArrayList<>();
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

    public User getMeetingOwner() {
        return meetingOwner;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}