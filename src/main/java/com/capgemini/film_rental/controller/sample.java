package com.capgemini.film_rental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/check-db")
public class sample {
    @GetMapping
    public String text(){
        return "Hello Controller";
    }
}
