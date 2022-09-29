package com.springboot.mybus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private List<Booked> bookingList;

    public User() {
    }

    public User(String username, String password, String email, String role, List<Booked> bookingList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.bookingList = bookingList;
    }
}
