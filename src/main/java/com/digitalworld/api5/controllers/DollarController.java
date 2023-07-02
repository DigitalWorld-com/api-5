package com.digitalworld.api5.controllers;

import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.impl.DollarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DollarEntity> saveDollarCurrency(@RequestParam String tipo){

        DollarEntity dollarEntity = dollarService.saveDollarData(tipo);

        return ResponseEntity.status(HttpStatus.CREATED).body(dollarEntity);

    }

}
