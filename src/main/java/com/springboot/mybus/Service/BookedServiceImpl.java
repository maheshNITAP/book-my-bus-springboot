package com.springboot.mybus.Service;

import com.springboot.mybus.model.Booked;
import com.springboot.mybus.repository.BookedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookedServiceImpl implements  BookedService{


    @Autowired
    private BookedRepository bookedRepository;
//
//    @Override
//    public List<Booked> findAll() {
//        return bookedRepository.findAll();
//    }
//
//    @Override
//    public Booked save(Booked booked) {
//        return bookedRepository.save(booked);
//    }
//
//    @Override
//    public Optional<Booked> findById(Integer theId) {
//        return bookedRepository.findById(theId);
//    }
//
//    @Override
//    public void deleteById(Integer theId) {
//        bookedRepository.deleteById(theId);
//    }

    @Override
    public List<Booked> getBookingsByuserId(Integer userId) {
        return bookedRepository.getBookingsByuserId(userId);
    }

}
