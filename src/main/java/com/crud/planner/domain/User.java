package com.crud.planner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    /*@ManyToMany(fetch = FetchType.EAGER)
    private List<Meeting> meetings;*/

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

    /*public List<Meeting> getMeetings() {
        return meetings;
    }*/
}
