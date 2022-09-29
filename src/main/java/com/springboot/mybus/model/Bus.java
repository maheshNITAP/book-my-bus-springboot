package com.springboot.mybus.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bus_id")
    private Integer busId;
    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "bus_name")
    private String busName;
    @Column(name = "bus_type")
    private String busType;
    @Column(name = "total_seats")
    private Integer totalSeats;


    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "bus_id")
    private List<Booked> bookingList;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "bus_id")
    private List<Route> routeList;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "bus_id")
    private List<Seat> seatList;

    public Bus() {
    }

    public Bus(String busNumber, String busName, String busType, Integer totalSeats) {
        this.busNumber = busNumber;
        this.busName = busName;
        this.busType = busType;
        this.totalSeats = totalSeats;
    }
}
