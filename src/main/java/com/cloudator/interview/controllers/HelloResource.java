package com.cloudator.interview.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @GetMapping("/hei")
    public String greetings() {
        return "Hei!";
    }
}