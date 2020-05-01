package com.crud.planner.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private String city;

    private String address;

    public Location() {
    }

    public Location(Long id, String description, String city, String address) {
        this.id = id;
        this.description = description;
        this.city = city;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
