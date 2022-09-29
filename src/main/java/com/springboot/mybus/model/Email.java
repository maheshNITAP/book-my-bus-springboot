package com.springboot.mybus.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {
    private  String to;
     private String message;

    public Email() {
    }

    public Email(String to, String message) {
        this.to = to;
        this.message = message;
    }
}
