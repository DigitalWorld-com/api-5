package com.digitalworld.api5.controllers;

import com.digitalworld.api5.model.DollarModel;
import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.impl.DollarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/dollar")
@RequiredArgsConstructor
public class DollarController {

    private final DollarService dollarService;

    @GetMapping("/data")
    public ResponseEntity<DollarDataResponse> getDollarData() {
        return ResponseEntity.ok(dollarService.getDollarData());
    }

    @PostMapping()
    public ResponseEntity<DollarModel> saveDollarCurrency(@RequestParam String tipo){

        DollarModel dollarModel = dollarService.saveDollarData(tipo);

        return ResponseEntity.status(HttpStatus.CREATED).body(dollarModel);

    }

}
