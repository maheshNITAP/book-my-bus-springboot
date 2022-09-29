package com.springboot.mybus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private Integer seatId;
    @Column(name = "seat_number")
    private Integer seatNumber;
    @Column(name = "seat_type")
    private String seatType;
    @Column(name = "seat_status")
    private String seatStatus;

    public Seat() {
    }

    public Seat(Integer seatNumber, String seatType, String seatStatus) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
    }
}
