package com.springboot.mybus.Service;

import com.springboot.mybus.model.Seat;
import com.springboot.mybus.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService{
//    @Autowired
//    private SeatRepository seatRepository;
//
//    @Override
//    public List<Seat> findAll() {
//        return seatRepository.findAll();
//    }
//
//    @Override
//    public Seat save(Seat seat) {
//        return seatRepository.save(seat);
//    }
//
//    @Override
//    public Optional<Seat> findById(Integer theId) {
//        return seatRepository.findById(theId);
//    }
//
//    @Override
//    public void deleteById(Integer theId) {
//        seatRepository.deleteById(theId);
//    }


}
