package com.crud.planner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    private String email;

    @ManyToOne
    private Group group;

    public User() {
    }

    public User(Long id, String firstname, String lastname, String email, Group group) {
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

    public Group getGroup() { return group; }
}
