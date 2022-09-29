package com.springboot.mybus.repository;

import com.springboot.mybus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
