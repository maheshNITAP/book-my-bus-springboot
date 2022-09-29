package com.springboot.mybus.repository;

import com.springboot.mybus.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.List;

public interface RouteRepository  extends JpaRepository<Route,Integer> {
    @Query(nativeQuery = true,value = "select r1.bus_id as r1busid from Route r1,Route r2 where r1.route_rank<r2.route_rank and r1.bus_id=r2.bus_id and r1.station_name=?1 and r2.station_name=?2 and r1.date=?3 and r2.date=?3")
    List<Integer>getBusBysourceDestination(String source,String Destination, String date);

    @Query(nativeQuery = true,value = "Select r3.booked_seats from route r1, route r2, route r3 where r1.station_name = :source and r2.station_name= :destination and r1.bus_id = :busId and  r2.bus_id= :busId and r3.route_rank < r2.route_rank and r3.route_rank >= r1.route_rank and r3.bus_id= :busId and r3.date= :date")
    HashSet<String>getSeatsBysourceDestination(String source, String destination, Integer busId, String date);

//    @Query(nativeQuery = true,value="update booke_seats from route r1 where r1.busId=:busId and r1.rank" );
//      void setSeatsBysourceDestination(String source, String destination,String );

//    @Query(nativeQuery = true,value = "Select distinct r3.* from route r1, route r2, route r3 where r1.station_name = :source and r2.station_name= :destination and r1.bus_id = :busId and  r2.bus_id= :busId and r3.route_rank < r2.route_rank and r3.route_rank >= r1.route_rank and r3.bus_id= :busId and r3.date=:date")
//    List<Route> getRoutesBySourceDestination(String source, String destination, Integer busId,String date);


    @Query(nativeQuery = true,value = "Select distinct r3.* from route r1, route r2, route r3 where r1.station_name = :source and r2.station_name= :destination and r1.bus_id = :busId and  r2.bus_id= :busId and r3.route_rank < r2.route_rank and r3.route_rank >= r1.route_rank and r3.bus_id= :busId and r3.date=:date")
    List<Route> getRoutesBySourceDestination(String source, String destination, Integer busId,String date);

//    @Query(nativeQuery = true,value = "Select r3.* from route r1, route r2, route r3 where r1.station_name = :source and r2.station_name= :destination and r1.bus_id = :busId and  r2.bus_id= :busId and r3.route_rank < r2.route_rank and r3.route_rank >= r1.route_rank and r3.bus_id= :busId ")
//    boolean cancelSeatsBysourceDestinationSeatIdbookingId(String source,String destination,Integer busId,Integer seatId);

    @Query(nativeQuery = true,value = "select * from route where bus_id = :busId and  time(schedule_time) <= time(:currentTime) and date(date)=date(:date)"+
            "order by schedule_time desc limit 1")
    Route getLatAndLngByBusId(Integer busId, String currentTime,String date);

    @Query(nativeQuery = true, value = "select * from route where bus_id= :busId and route_rank=1 and date(date)=date(:date)")
    Route getFirstLatAndLangByBusId(Integer busId,String date);


    @Query(nativeQuery = true,value = "select * from route where date(date)=date(:date)" )
    List<Route> getAllRoutesByDate(String date);

}



























