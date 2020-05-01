package com.crud.planner.domain;

public class LocationDto {

    private Long id;
    private String description;
    private String city;
    private String address;

    public LocationDto(Long id, String description, String city, String address) {
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
