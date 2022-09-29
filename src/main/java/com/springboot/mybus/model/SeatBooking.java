package com.springboot.mybus.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatBooking {

    private Boolean flag;

    private String origin;

    private String destination;

    private Integer busId;

    private String date;

    List<String> seatNo;

    public SeatBooking() {
    }

    public SeatBooking(Boolean flag, String origin, String destination, Integer busId, String date, List<String> seatNo) {
        this.flag = flag;
        this.origin = origin;
        this.destination = destination;
        this.busId = busId;
        this.date = date;
        this.seatNo = seatNo;
    }
}
