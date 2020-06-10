package com.crud.planner.domain;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingDto {

    private Long id;
    private String topic;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Location location;
    private List<User> participants;
    private String content;

    public MeetingDto(Long id, String topic, LocalDateTime startDate, LocalDateTime endDate, Location location, List<User> participants, String content) {
        this.id = id;
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.participants = participants;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
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

    public String getContent() {
        return content;
    }

}
