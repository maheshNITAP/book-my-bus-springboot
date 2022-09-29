package com.springboot.mybus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mybus.Service.SeatService;
import com.springboot.mybus.model.Seat;
import com.springboot.mybus.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seat-api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class SeatController {
//    @Autowired
//    private SeatService seatService;
//
//    @GetMapping("/seats")
//    public List<Seat> getSeats(){
//        return seatService.findAll();
//    }
//
//    @PostMapping("/save")
//    public Seat save(@RequestBody Seat seat){
//        return seatService.save(seat);
//    }
//
//    @GetMapping("/seats/{theId}")
//    public Optional<Seat> findById(@PathVariable Integer theId){
//        return seatService.findById(theId);
//    }
//
//    @DeleteMapping("/seats/{theId}")
//    public void deleteById(@PathVariable Integer theId){
//        seatService.deleteById(theId);
//    }

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/seats")
    ResponseEntity<?> findSeats(){
        List<Seat> seats=seatRepository.findAll();
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/seats/{theId}")
    ResponseEntity<?> findSeat(@PathVariable(value = "theId") int id){
        Optional<Seat> seat=seatRepository.findById(id);

        if(seat.isPresent()){
            return new ResponseEntity<>(seat.get(),HttpStatus.OK);
        }
        else {
            throw  new RuntimeException("The seat is NOT Found");
        }
    }

    @PostMapping("/save")
    ResponseEntity<?> addSeat(@RequestBody String seat) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        Seat newSeat=objectMapper.readValue(seat,Seat.class);
        Seat createSeat=seatRepository.save(newSeat);
        return new ResponseEntity<>(createSeat,HttpStatus.OK);
    }

    @DeleteMapping("/seats/{theId}")
    ResponseEntity<?> deleteSeat(@PathVariable(value ="theId")int id){
        Optional<Seat> seat=seatRepository.findById(id);
        seatRepository.deleteById(id);
        return new ResponseEntity<>("Deleted seat id"+seat.get().getSeatId(),HttpStatus.OK);
    }

}
