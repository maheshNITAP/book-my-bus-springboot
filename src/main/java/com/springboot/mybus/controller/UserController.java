package com.springboot.mybus.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springboot.mybus.Service.UserService;
import com.springboot.mybus.model.User;
import com.springboot.mybus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/users")
//    public List<User> getUsers(){
//        return  userService.findAll();
//    }
//
//    @PostMapping("/save")
//    public  User save(@RequestBody User user){
//        return  userService.save(user);
//    }
//
//    @GetMapping("/users/{theId}")
//    public Optional<User> findById(@PathVariable Integer theId){
//        return userService.findById(theId);
//    }
//
//    @DeleteMapping("/users/{theId}")
//    public void deleteById(@PathVariable Integer theId){
//        userService.deleteById(theId);
//    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/users")
    ResponseEntity<?> findUser(){
        List<User> user=userRepository.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{theId}")
    ResponseEntity<?> findUser(@PathVariable(value = "theId")int id){
        Optional<User> user=userRepository.findById(id);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }
        else{
            throw  new RuntimeException("The user is not found.");
        }
    }

    @PostMapping("/save")
    ResponseEntity<?> addUser(@RequestBody User user) throws JsonProcessingException {
        User savedUser=userRepository.save(user);

        return new ResponseEntity<>(savedUser,HttpStatus.OK);
    }

    @DeleteMapping("/users/{theId}")
    ResponseEntity<?> deleteUser(@PathVariable(value = "theId")int id){
        Optional<User> user=userRepository.findById(id);
        userRepository.deleteById(id);
        return new ResponseEntity<>("Delete user id : "+user.get().getUserId(),HttpStatus.OK);
    }


//for user DAO
    @PostMapping("/login")
    public List<User> detailsCheck(@RequestBody User user){
        List<User> theUser=userService.loginCheck(user);
        return theUser;

    }

}



















