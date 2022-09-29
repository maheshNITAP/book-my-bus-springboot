package com.springboot.mybus.repository;

import com.springboot.mybus.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository  extends JpaRepository<Seat,Integer> {
}
