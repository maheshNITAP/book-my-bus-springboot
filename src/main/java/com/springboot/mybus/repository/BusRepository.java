package com.springboot.mybus.repository;

import com.springboot.mybus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository  extends JpaRepository<Bus,Integer> {
}
