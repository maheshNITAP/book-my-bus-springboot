package com.springboot.mybus.controller;

import com.springboot.mybus.Service.EmailService;
import com.springboot.mybus.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Mail")
@CrossOrigin(origins = {"http://localhost:3000"})
public class EmailController {
    @Autowired
    EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){
        return "this is welcome";
    }
    @PostMapping("/sendemail")
    ResponseEntity<?> sendEmail(@RequestBody Email email){

        System.out.println(email);
        boolean f=emailService.sendEmail(email.getMessage(),email.getTo());

        if(f){
            return  new ResponseEntity<>("Done", HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("Some error", HttpStatus.OK);
        }

    }

}
