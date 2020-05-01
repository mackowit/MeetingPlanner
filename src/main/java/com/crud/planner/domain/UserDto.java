package com.crud.planner.domain;

public class UserDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Group group;

    public UserDto(Long id, String firstname, String lastname, String email, Group group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Group getGroup() {
        return group;
    }
}
