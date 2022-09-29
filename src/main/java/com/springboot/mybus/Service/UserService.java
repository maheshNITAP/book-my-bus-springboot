package com.springboot.mybus.Service;

import com.springboot.mybus.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    public List<User> findAll();
//
//    User save(User user);
//
//    Optional<User> findById(Integer theId);
//
//    void deleteById(Integer theId);

    public List<User> loginCheck(User user);
}
