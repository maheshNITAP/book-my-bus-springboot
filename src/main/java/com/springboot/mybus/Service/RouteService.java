package com.springboot.mybus.Service;

import com.springboot.mybus.model.Bus;
import com.springboot.mybus.model.Route;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RouteService {
//    public List<Route> findAll();
//
//    Route save(Route route);
//
//    Optional<Route> findById(Integer theId);
//
//    void deleteById(Integer theId);
//
      List<Bus> getBusesBySourceAndDestination(String origin,String destination,String date);

      HashSet<String> getSeatsBySourceAndDestination(String origin,String destination,Integer busId,String date);

//      boolean booksSeatsBySourceDestinationAndBusId(String origin, String destination, Integer busId, List<String> seatNo, String date);

      boolean booksSeatsBySourceDestinationAndBusId(Boolean flag,String origin, String destination, Integer busId, List<String> seatNo, String date);
      boolean cancelSeatsBySourceDestinationAndBusIdAndBookingId(String origin,String destination,Integer busId,String seatId, String date);
//      boolean cancelReservedSeatsBySourceDestinationAndBusIdAndBookingId(String origin, String destination, Integer busId, String seatId,String date);
      List<String> getLatAndLngByBusId(Integer busId,String currentTime,String date);


      List<Route> saveAllRoutes(List<Route> routes);

      List<Route> getAllRoutesByDate(String date);

}

