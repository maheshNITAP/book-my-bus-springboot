package com.springboot.mybus.repository;

import com.springboot.mybus.model.Booked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRepository  extends JpaRepository<Booked,Integer>{
    @Query(nativeQuery = true,value = "select * from booked where user_id = :userId")
    List<Booked> getBookingsByuserId(Integer userId);


}
