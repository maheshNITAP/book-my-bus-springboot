package com.springboot.mybus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id")
    private Integer routeId;
    @Column(name = "station_name")
    private String stationName;
    @Column(name = "bus_id")
    private Integer busId;
    @Column(name = "schedule_time")
    private LocalTime scheduleTime;
    @Column(name = "route_rank")
    private Integer routeRank;
    @Column(name = "distance")
    private Integer distance;
    @Column(name = "price")
    private Integer price;

    @Column(name="date")
    private String date;

    @Column(name="booked_seats")
    private String bookedSeats;
    @Column(name="reserved_seats")
    private String reservedSeats;
    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;


    public Route() {
    }

//    public Route(String stationName, Integer busId, LocalTime scheduleTime, Integer routeRank, Integer distance, Integer price, String date, String bookedSeats, String lat, String lng) {
//        this.stationName = stationName;
//        this.busId = busId;
//        this.scheduleTime = scheduleTime;
//        this.routeRank = routeRank;
//        this.distance = distance;
//        this.price = price;
//        this.date = date;
//        this.bookedSeats = bookedSeats;
//        this.lat = lat;
//        this.lng = lng;
//    }


    public Route(String stationName, Integer busId, LocalTime scheduleTime, Integer routeRank, Integer distance, Integer price, String date, String bookedSeats, String reservedSeats, String lat, String lng) {
        this.stationName = stationName;
        this.busId = busId;
        this.scheduleTime = scheduleTime;
        this.routeRank = routeRank;
        this.distance = distance;
        this.price = price;
        this.date = date;
        this.bookedSeats = bookedSeats;
        this.reservedSeats = reservedSeats;
        this.lat = lat;
        this.lng = lng;
    }
}
