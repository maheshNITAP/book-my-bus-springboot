package com.springboot.mybus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mybus.Service.BookedService;
import com.springboot.mybus.model.Booked;
import com.springboot.mybus.model.Route;
import com.springboot.mybus.repository.BookedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/booked-api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BookedController
{

    @Autowired
    BookedRepository bookedRepository;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BookedService bookedService;

//@Autowired
//BookedService bookedService;
//    @GetMapping("/bookings")
//    public List<Booked> getBooked(){
//        return bookedService.findAll();
//    }
//
//    @PostMapping("/save")
//    public Booked save(@RequestBody Booked booked){
//        return bookedService.save(booked);
//    }
//
//    @GetMapping("/bookings/{theId}")
//    public Optional<Booked> findById(@PathVariable Integer theId){
//        return bookedService.findById(theId);
//    }
//
//    @DeleteMapping("/bookings/{theId}")
//    public  void  deleteById(@PathVariable Integer theId){
//        bookedService.deleteById(theId);
//    }


    @GetMapping("/bookings")
    ResponseEntity<?> findBookings(){
        List<Booked> booked=bookedRepository.findAll();
        return new ResponseEntity<>(booked, HttpStatus.OK);
    }

    @GetMapping("/bookings/{theId}")
    ResponseEntity<?> findBooking(@PathVariable(value="theId") Integer bookingId){
        Optional<Booked> booked=bookedRepository.findById(bookingId);

        if(booked.isPresent()){
            return new ResponseEntity<>(booked.get(),HttpStatus.OK);
        }
        else{
            throw new RuntimeException("This Booking id"+bookingId+ "  not found");
        }
    }

    @PostMapping("/save")
    ResponseEntity<?> addBooking(@RequestBody String booking) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        Booked newBooking=objectMapper.readValue(booking,Booked.class);
        Booked createBooking= bookedRepository.save(newBooking);

        return new ResponseEntity<>(createBooking,HttpStatus.OK);
    }
    @DeleteMapping("/bookings/{theId}")
    ResponseEntity<?> deleteBooking(@PathVariable(value="theId") Integer bookingId){
        Optional<Booked> booking= bookedRepository.findById(bookingId);
        bookedRepository.deleteById(bookingId);
        return new ResponseEntity<>("Delete id "+booking.get().getBookingId(),HttpStatus.OK);
    }

    @PostMapping("/bookedTicket")
    ResponseEntity<?> ticketBooking(@RequestBody List<Booked> bookings) throws JsonProcessingException{

//        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//
//        List<Booked> booking=objectMapper.convertValue(bookings, new TypeReference<List<Booked>>() {
//        });
//        List<Booked> createBooking=bookedRepository.saveAll(booking);
//        return new ResponseEntity<>(createBooking,HttpStatus.OK);
        //return new ResponseEntity<>(bookedRepository.saveAll(bookings), HttpStatus.OK);

        //List<Booked> bookings=objectMapper.readValue(bookingsList, new TypeReference<List<Booked>>(){});
        return new ResponseEntity<>(bookedRepository.saveAll(bookings),HttpStatus.OK);
    }

    @GetMapping("/byUserId/{userId}")
    ResponseEntity<?> bookingList(@PathVariable(value = "userId") Integer userId){
        List<Booked> bookings= bookedService.getBookingsByuserId(userId);
        return  new ResponseEntity<>(bookings,HttpStatus.OK);
    }

    @PutMapping("/cancelTicket/{bookingId}")
    ResponseEntity<?> cancelTicket(@PathVariable(value = "bookingId") Integer bookingId) throws JsonProcessingException{
        String status="cancelled";
        Optional<Booked> cancelBooked=bookedRepository.findById(bookingId);
        cancelBooked.ifPresent(booked -> {
            booked.setStatus(status);
            bookedRepository.save(booked);
        });
        return  new ResponseEntity<>("Ticket Canceled",HttpStatus.OK);
    }




}
