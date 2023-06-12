package com.digitalworld.api5.controllers;

import com.digitalworld.api5.request.WeatherRequest;
import com.digitalworld.api5.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

//    @GetMapping("/current")
//    public ResponseEntity<WeatherResponse> getCurrentWeather(WeatherRequest request){
//        return ResponseEntity.of(weatherService.getCurrentWeather(request));
//    }


}
