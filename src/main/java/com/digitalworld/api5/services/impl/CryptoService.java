package com.digitalworld.api5.services.impl;

import com.digitalworld.api5.exception.CryptoApiException;
import com.digitalworld.api5.integrations.CryptoApiIntegration;
import com.digitalworld.api5.mapper.CryptoMapper;
import com.digitalworld.api5.persistence.CryptoRepository;
import com.digitalworld.api5.responses.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoApiIntegration cryptoApi;

    private final CryptoRepository repository;
    private final CryptoMapper mapper;

    public Map<String, CryptoCurrency.Currency> getCryptoCurrencies(String cryptoNames){

        String vsCurrency  = "usd";

        Optional<Map<String, CryptoCurrency.Currency>> apiResponse = cryptoApi.getCryptoInfo(cryptoNames, vsCurrency);

        if (apiResponse.isEmpty()) throw new CryptoApiException("Failed to retrieve cryptocurrency prices");
        repository.save(mapper.dataToCryptoEntity(cryptoNames, vsCurrency, apiResponse.get().get("bitcoin").getUsd(), LocalDateTime.now()));
        return apiResponse.get();
    }

}
