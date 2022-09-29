package com.springboot.mybus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springboot.mybus.Service.RouteService;
import com.springboot.mybus.model.Bus;
import com.springboot.mybus.model.Route;
import com.springboot.mybus.model.SeatBooking;
import com.springboot.mybus.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/route-api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RouteController {

//    @Autowired
//    private RouteService routeService;
//
//    @GetMapping("/routes")
//    public List<Route> getRouts(){
//        return routeService.findAll();
//    }
//
//    @PostMapping("/save")
//    public Route save(@RequestBody Route route){
//        return routeService.save(route);
//    }
//
//    @GetMapping("/routes/{theId}")
//    public Optional<Route> findById(@PathVariable Integer theId){
//        return routeService.findById(theId);
//    }
//
//    @DeleteMapping("/routes/{theId}")
//    public void deleteById(@PathVariable Integer theId){
//        routeService.deleteById(theId);
//    }
    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RouteService routeService;

    @GetMapping("/routes")
    ResponseEntity<?> findRoutes(){
        List<Route> routes = routeRepository.findAll();
        return new ResponseEntity<>(routes, HttpStatus.OK) ;
    }

    @GetMapping("/routes/{theId}")
    ResponseEntity<?> findRoute(@PathVariable(value = "theId") int id){
        Optional<Route> route= routeRepository.findById(id);
        if(route.isPresent())
        {
            return new ResponseEntity<>(route.get(),HttpStatus.OK);
        }
        else
        {
            throw new RuntimeException("The route  of id :"+id+"  is not found");
        }
    }

//    @PostMapping("/save")
//    ResponseEntity<?> addRoute(@RequestBody String route) throws JsonProcessingException {
//
//        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        Route newRoute = objectMapper.readValue(route, Route.class);
//        Route createdRoute = routeRepository.save(newRoute);
//
//        return new ResponseEntity<>(createdRoute,HttpStatus.OK);
//    }

    @PostMapping("/save")
    ResponseEntity<?> addRoute(@RequestBody Route route) throws JsonProcessingException {
        return new ResponseEntity<>(routeRepository.save(route),HttpStatus.OK);
    }
    @DeleteMapping("/routes/{theId}")
    ResponseEntity<?> deleteRoute(@PathVariable(value = "theId") int id){
        Optional<Route> route= routeRepository.findById(id);
        routeRepository.deleteById(id);
        return new ResponseEntity<>("Delete route to " + route.get().getStationName()+" and ID is : "+route.get().getRouteId(),HttpStatus.OK);
    }

    //bus id for source destination
//    @GetMapping("/busesId")
//    ResponseEntity<?> findBuses( @Param(value="origin") String origin, @Param(value="destination") String destination) throws JsonProcessingException {
//        List<Bus> buses= routeService.getBusesBySourceAndDestination(origin,destination);
//
//        if(!buses.isEmpty()){
//            return new ResponseEntity<>(new CommonResponse(HttpStatus.OK.value(),
//                    HttpStatus.OK.getReasonPhrase(),
//                    objectMapper.writeValueAsString(buses)),HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(new CommonResponse(HttpStatus.NOT_FOUND.value(),
//                    HttpStatus.NOT_FOUND.getReasonPhrase(),
//                    "No bus starts from " + origin),HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/update")
    ResponseEntity<?> updateRoute(@RequestBody String route) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        Route newRoute = objectMapper.readValue(route, Route.class);
        Route createdRoute = routeRepository.save(newRoute);

        return new ResponseEntity<>(createdRoute,HttpStatus.OK);
    }

    @GetMapping("/busesId")
    ResponseEntity<?> findBuses(@Param(value="origin") String origin, @Param(value="destination") String destination,@Param(value="date")String date) throws JsonProcessingException {
        List<Bus> buses= routeService.getBusesBySourceAndDestination(origin,destination,date);

        if(!buses.isEmpty()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(buses),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Bus no Exist",HttpStatus.OK);
        }
    }


    @GetMapping("/AvailableSeats")
    ResponseEntity<?> findSeats(@Param(value = "origin") String origin,@Param(value = "destination") String destination,@Param(value ="busId") Integer busId,@Param(value="date")String date) throws JsonProcessingException{
        HashSet<String> bookedSeats=routeService.getSeatsBySourceAndDestination(origin,destination,busId,date);
        HashSet<String> allSeatsFree=new HashSet<>();

            return new ResponseEntity<>(objectMapper.writeValueAsString(bookedSeats),HttpStatus.OK);

    }


//    @PutMapping("/bookingSeats")
//    ResponseEntity<?> bookingSeats(@RequestParam List<String> seatNo,@Param(value = "origin") String origin,@Param(value = "destination") String destination,@Param(value = "busId")Integer busId) throws JsonProcessingException{
//        boolean isBooked = routeService.booksSeatsBySourceDestinationAndBusId(origin,
//                destination,busId,seatNo);
//        if (isBooked)
//        {
//            return new ResponseEntity<>("Successfully booked seats "+
//                    seatNo +" from " + origin + " to " + destination,HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>("Error booking seats",HttpStatus.OK);
//        }
//    }

    @PutMapping("/bookingSeats")
    ResponseEntity<?> bookingSeats(@RequestBody SeatBooking seatBooking) throws JsonProcessingException{
        boolean isBooked = routeService.booksSeatsBySourceDestinationAndBusId(seatBooking.getFlag(),seatBooking.getOrigin(),
                seatBooking.getDestination(),seatBooking.getBusId(),seatBooking.getSeatNo(),seatBooking.getDate());
        if (isBooked)
        {
            return new ResponseEntity<>("Successfully booked seats "+
                    seatBooking.getSeatNo() +" from " + seatBooking.getOrigin() + " to " + seatBooking.getDestination(),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Error booking seats",HttpStatus.OK);
        }
    }




    @PostMapping("/assignRoutes")
    ResponseEntity<?> assignRoutes(@RequestBody List<Route> routes) throws  JsonProcessingException{

        return  new ResponseEntity<>(routeService.saveAllRoutes(routes),HttpStatus.OK);
    }



    @PutMapping("/cancelSeats")
    ResponseEntity<?> cancelSeats(@Param(value = "origin") String origin,@Param(value = "destination") String destination,@Param(value = "busId")Integer busId,@Param(value = "seatId")String seatId,@Param(value="date")String date)throws JsonProcessingException{
        boolean isCancelled=routeService.cancelSeatsBySourceDestinationAndBusIdAndBookingId(origin,destination,busId,seatId,date);
        if(isCancelled){
            return new ResponseEntity<>("Seat no "+seatId+"is cancelled successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Error in cancelling seat booking",HttpStatus.OK);
        }
    }

//    @PutMapping("/cancelReservedSeats")
//    ResponseEntity<?> cancelReservedSeats(@Param(value = "origin") String origin,@Param(value = "destination") String destination,@Param(value = "busId")Integer busId,@Param(value = "seatId")String seatId,@Param(value="date")String date)throws JsonProcessingException{
//        boolean isCancelled=routeService.cancelReservedSeatsBySourceDestinationAndBusIdAndBookingId(origin,destination,busId,seatId,date);
//        if(isCancelled){
//            return new ResponseEntity<>("Seat no "+seatId+"is cancelled successfully",HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>("Error in cancelling seat booking",HttpStatus.OK);
//        }
//    }


    @GetMapping("/mapsApi")
    ResponseEntity<?> getLatandLng(@Param(value = "busId")Integer busId,@Param(value = "currentTime")String currentTime,@Param(value = "date")String date) throws  JsonProcessingException{
                List<String> latlang=routeService.getLatAndLngByBusId(busId,currentTime,date);

                if(!latlang.isEmpty()){
                    return  new ResponseEntity<>(latlang,HttpStatus.OK);
                }
                else {
                    return  new ResponseEntity<>("OK NOT OK",HttpStatus.OK);
                }
    }


    @GetMapping("/todayRoutes")
    ResponseEntity<?> getTodaysRoutes(@Param(value = "date")String date){
        List<Route> routes=routeService.getAllRoutesByDate(date);
        return new ResponseEntity<>(routes,HttpStatus.OK);
    }


}





















