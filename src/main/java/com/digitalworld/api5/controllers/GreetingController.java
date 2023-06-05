package com.digitalworld.api5.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-5")
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(){
        return "Hola! Somos el grupo 5!";
    }
}