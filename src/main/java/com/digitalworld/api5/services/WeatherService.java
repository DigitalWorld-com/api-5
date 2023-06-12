package com.digitalworld.api5.services;

import com.digitalworld.api5.exception.MessageException;
import com.digitalworld.api5.integrations.WeatherApiIntegration;
import com.digitalworld.api5.model.GeolocationModel;
import com.digitalworld.api5.request.WeatherRequest;
import com.digitalworld.api5.responses.GeolocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherApiIntegration weatherApiIntegration;

//    public WeatherResponse getCurrentWeather(WeatherRequest request){
//        GeolocationResponse geoResponse = getGeolocation(request);
//
//    }

    private GeolocationResponse getGeolocation(WeatherRequest request) throws MessageException{
        List<GeolocationModel> geolocationList = weatherApiIntegration.getGeolocation(request.getCityName());
        GeolocationModel current = getCurrent(geolocationList, request.getCountryCode());
        return GeolocationResponse.builder()
                .lat(current.getLat())
                .lon(current.getLon())
                .build();
    }

    private GeolocationModel getCurrent(List<GeolocationModel> geoList, String countryCode){
        for (GeolocationModel model : geoList){
            if(model.getCountry().equals(countryCode))
                return model;
        }
        return GeolocationModel.builder().build();
    }

}
