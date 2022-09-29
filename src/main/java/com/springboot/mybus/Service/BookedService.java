package com.springboot.mybus.Service;

import com.springboot.mybus.model.Booked;

import java.util.List;
import java.util.Optional;

public interface BookedService {

//    public List<Booked> findAll();
//
//    Booked save(Booked booked);
//
//    Optional<Booked> findById(Integer theId);
//
//    void deleteById(Integer theId);

    List<Booked> getBookingsByuserId(Integer userId);
}
