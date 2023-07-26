package com.digitalworld.api5.controllers;

import com.digitalworld.api5.entity.ConversionEntity;
import com.digitalworld.api5.requests.ConversionRequest;
import com.digitalworld.api5.responses.ConversionResponse;
import com.digitalworld.api5.services.impl.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService service;

    @PostMapping("/dollar-pesos")
    public ResponseEntity<ConversionResponse> convertDollarPesos(@RequestBody ConversionRequest request){
        return ResponseEntity.ok(service.convertDollarsToPesos(request));
    }

    @GetMapping("/allconversions")
    public ResponseEntity<List<ConversionEntity>> getAllCoversions(){
        return ResponseEntity.ok(service.getHistoricData());
    }

    public String greeting(){
        return "Hola! Somos el grupo 5!";
    }
}