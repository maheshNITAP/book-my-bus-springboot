package com.springboot.mybus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private String stationName;

    private Integer busId;

    private LocalTime scheduleTime;

    private Integer routeRank;

    private Integer distance;

    private Integer price;

    private String date;

    private String lat;

    private String lng;


}
