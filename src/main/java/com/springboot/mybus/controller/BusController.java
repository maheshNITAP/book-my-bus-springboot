package com.springboot.mybus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springboot.mybus.Service.BusService;
import com.springboot.mybus.model.Bus;
import com.springboot.mybus.model.User;
import com.springboot.mybus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buses-api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BusController {

//    @Autowired
//    private BusService busService;
//
//    @GetMapping("/buses")
//    public List<Bus> getBuses(){
//        return busService.findAll();
//    }
//
//    @PostMapping("/save")
//    public  Bus save(@RequestBody Bus bus){
//        return busService.save(bus);
//    }
//
//    @GetMapping("/buses/{theId}")
//    public Optional<Bus> findById(@PathVariable Integer theId){
//        return busService.findById(theId);
//    }
//
//    @DeleteMapping("/buses/{theId}")
//    public void deleteById(@PathVariable Integer theId){
//        busService.deleteById(theId);
//    }

    @Autowired
    BusRepository busRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BusService busService;

    @GetMapping("/buses")
    ResponseEntity<?> findBuses(){
        List<Bus> buses=busRepository.findAll();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/buses/{theId}")
    ResponseEntity<?> findBus(@PathVariable(value = "theId") int id){
        Optional<Bus> bus=busRepository.findById(id);

        if(bus.isPresent()){
            return new ResponseEntity<>(bus.get(),HttpStatus.OK);
        }
        else{
            throw  new RuntimeException("This Bus id"+id+ "NOT found.");
        }
    }

//    @PostMapping("/save")
//    ResponseEntity<?> addBus(@RequestBody String bus) throws JsonProcessingException {
//        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//
//        Bus newBus=objectMapper.readValue(bus,Bus.class);
//        Bus createBus=busRepository.save(newBus);
//        return new ResponseEntity<>(createBus,HttpStatus.OK);
//    }

    @PostMapping("/save")
    ResponseEntity<?> addBus(@RequestBody Bus bus) throws JsonProcessingException {

        return new ResponseEntity<>(busRepository.save(bus),HttpStatus.OK);
    }

    @PutMapping("/updateBus")
    public  Bus updateBus(@RequestBody Bus bus)
    {
        return busService.updateBus(bus);
    }

    @DeleteMapping("/buses/{theId}")
    ResponseEntity<?> deleteBus(@PathVariable(value = "theId") int id){
        Optional<Bus> bus=busRepository.findById(id);
        busRepository.deleteById(id);
        return  new ResponseEntity<>("Deleted id  is:"+bus.get().getBusId(),HttpStatus.OK);
    }

}
