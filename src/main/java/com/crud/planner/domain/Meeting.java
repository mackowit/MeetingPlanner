package com.crud.planner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String topic;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @ManyToOne
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> participants;

    private String content;

    public Meeting() {
    }

    public Meeting(Long id, String topic, LocalDateTime startDate, LocalDateTime endDate, Location location, List<User> participants, String content) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "\n" + topic + ":\n\t" + datesToString(startDate, endDate) + "\n\t at: " + location + "\n";
    }

    public String datesToString(LocalDateTime startDate, LocalDateTime endDate) {
        return "from: " + startDate.getDayOfMonth() + "." + startDate.getMonthValue() + "." + startDate.getYear() + " " + startDate.getHour() +
                " to: " + endDate.getDayOfMonth() + "." + endDate.getMonthValue() + "." + endDate.getYear() + " " + endDate.getHour();
    }
}
