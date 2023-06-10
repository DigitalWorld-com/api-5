package com.digitalworld.api5.services;

import com.digitalworld.api5.exception.MessageException;
import com.digitalworld.api5.integrations.DollarApiIntegration;
import com.digitalworld.api5.model.DollarModel;
import com.digitalworld.api5.responses.DollarDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DollarService {

    private final DollarApiIntegration dollarApi;

    public DollarDataResponse getDollarData() throws MessageException{
        DollarModel officialData = getDollarInfo("/oficial");
        DollarModel blueData = getDollarInfo("/blue");
        if(Optional.ofNullable(officialData).isEmpty() || Optional.ofNullable(blueData).isEmpty())
            throw new MessageException("No se obtuvo resultado buscando datos");
        return DollarDataResponse.builder()
                .officialName(officialData.getNombre())
                .officialBuyPrice(officialData.getCompra())
                .officialSellPrice(officialData.getVenta())
                .officialUpdated(officialData.getFechaActualizacion())
                .blueName(blueData.getNombre())
                .blueBuyPrice(blueData.getCompra())
                .blueSellPrice(blueData.getVenta())
                .blueUpdated(blueData.getFechaActualizacion())
                .build();
    }

    private DollarModel getDollarInfo(String type){
        return dollarApi.getDollarInfo(type);
    }

}
