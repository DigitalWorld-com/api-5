package com.digitalworld.api5.controllers;

import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.DollarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dollar")
@RequiredArgsConstructor
public class DollarController {

    private final DollarService dollarService;

    @GetMapping("/data")
    public ResponseEntity<DollarDataResponse> getDollarData() {
        return ResponseEntity.ok(dollarService.getDollarData());
    }
}
