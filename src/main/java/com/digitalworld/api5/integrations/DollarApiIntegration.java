package com.digitalworld.api5.integrations;

import com.digitalworld.api5.configuration.ConfigurationApp;
import com.digitalworld.api5.model.DollarApiModel;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class DollarApiIntegration {

    private final RestTemplate restTemplate;

    private final ConfigurationApp configurationApp;

    public /*Map<String, DollarModel>*/DollarApiModel getDollarInfo(String dollarType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(configurationApp.getDollarBaseUrl() + "/" + dollarType);
            HttpEntity<Map<String, CryptoCurrency.Currency>> entity = new HttpEntity<>(headers);
            //Map<String, DollarModel> response = restTemplate.exchange(
            return restTemplate.exchange(
                    url.toUriString(),
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<DollarApiModel>(){
                    }
            ).getBody();
        } catch (RestClientException e) {
            log.error(":::::: Error : {} :::::::", e.getMessage());
            return null;
        }

    }

}
