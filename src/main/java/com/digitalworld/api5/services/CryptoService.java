package com.digitalworld.api5.services;

import com.digitalworld.api5.exception.CryptoApiException;
import com.digitalworld.api5.integrations.CryptoApiIntegration;
import com.digitalworld.api5.responses.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoApiIntegration cryptoApi;

    public Map<String, CryptoCurrency.Currency> getCryptoCurrencies(String cryptoNames){

        String vsCurrency  = "usd";

        Optional<Map<String, CryptoCurrency.Currency>> apiResponse = cryptoApi.getCryptoInfo(cryptoNames, vsCurrency);

        if (apiResponse.isEmpty()) throw new CryptoApiException("Failed to retrieve cryptocurrency prices");
        return apiResponse.get();

    }

}
