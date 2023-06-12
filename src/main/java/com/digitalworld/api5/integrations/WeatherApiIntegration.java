package com.digitalworld.api5.integrations;

import com.digitalworld.api5.configuration.ConfigurationApp;
import com.digitalworld.api5.model.GeolocationModel;
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

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherApiIntegration {

    private final RestTemplate restTemplate;
    private final ConfigurationApp configurationApp;

    public List<GeolocationModel> getGeolocation(String cityName){
        try {
            String baseUri = configurationApp.getGeolocationUrl();
            String limit = "5";
            String apiKey = "59a11fa8b43336a81a1d1182c5eb509a";
//            String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
//            String encodedApiKey = URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
            String uri = baseUri + "?q=" + cityName + "&limit=" + limit + "&appid=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(uri);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(
                    url.toUriString(),
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<GeolocationModel>>(){
                    }
            ).getBody();
        }
        catch (RestClientException ex){
            log.error(":::::: Error : {} :::::::", ex.getMessage());
            return null;
        }
    }
}
