package com.epam.ear.twitter.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "UserTable")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="author")
    public List<Tweet> tweets;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
