package com.digitalworld.api5.controllers;

import com.digitalworld.api5.responses.CryptoCurrency;
import com.digitalworld.api5.services.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService service;

    @GetMapping("/crypto-prices")
    public ResponseEntity<Map<String, CryptoCurrency.Currency>> getCryptoPrices(@RequestParam("ids") String ids) {
        return ResponseEntity.ok(service.getCryptoCurrencies(ids));
    }

}
