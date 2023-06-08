package com.digitalworld.api5.integrations;

import com.digitalworld.api5.configuration.ConfigurationApp;
import com.digitalworld.api5.responses.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CryptoApiIntegration {

    private final RestTemplate restTemplate;

    private final ConfigurationApp configurationApp;

    public Optional<Map<String, CryptoCurrency.Currency>> getCryptoInfo(String cryptoIds, String vsCurrency) {

        try {
            HttpHeaders headers = new HttpHeaders();

            String url = configurationApp.getCryptoUrl() + "?" +
                    "ids=" + cryptoIds +

//            for (String crypto:cryptoIds.split(",")) url.append("ids").append("=").append(crypto).append("%2C");

                    "&vs_currencies" + "=" + vsCurrency;

            HttpEntity<Map<String, CryptoCurrency.Currency>> entity = new HttpEntity<>(headers);

            Map<String, CryptoCurrency.Currency> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, CryptoCurrency.Currency>>(){
                    }
            ).getBody();

            return Optional.of(response);
        } catch (RestClientException e) {
            log.error(":::::: Error : {} :::::::", e.getMessage());
            return Optional.empty();
        }

    }

}
