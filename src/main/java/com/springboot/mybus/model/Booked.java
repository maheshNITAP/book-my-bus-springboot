package com.springboot.mybus.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Booked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;
    @Column(name = "starting_station")
    private String startingStation;
    @Column(name = "destination_station")
    private String destinationStation;
    @Column(name="arrival_time")
    private String arrivalTime;
    @Column(name = "departure_time")
    private String departureTime;
    @Column(name = "total_distance")
    private String totalDistance;
    @Column(name = "total_price")
    private String totalPrice;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "seat_id")
    private String seatId;
    @Column(name="name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private String age;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name="bus_type")
    private String busType;

    @Column(name="bus_id")
    private String busId;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    public Booked() {}

    public Booked(String startingStation, String destinationStation, String arrivalTime, String departureTime, String totalDistance, String totalPrice, String userId, String seatId, String name, String gender, String age, String busName, String busNumber, String busType, String busId, String date, String status) {
        this.startingStation = startingStation;
        this.destinationStation = destinationStation;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.totalDistance = totalDistance;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.seatId = seatId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.busName = busName;
        this.busNumber = busNumber;
        this.busType = busType;
        this.busId = busId;
        this.date = date;
        this.status = status;
    }
}
