package com.digitalworld.api5.services.impl;

import com.digitalworld.api5.exception.MessageException;
import com.digitalworld.api5.integrations.DollarApiIntegration;
import com.digitalworld.api5.mapper.DollarMapper;
import com.digitalworld.api5.model.DollarModel;
import com.digitalworld.api5.persistence.DollarRepository;
import com.digitalworld.api5.responses.DollarApiResponse;
import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.DollarServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DollarService implements DollarServiceApi {

    private final DollarApiIntegration dollarApi;
    private final DollarRepository repository;
    private final DollarMapper mapper;

    public DollarDataResponse getDollarData() throws MessageException{
        DollarApiResponse officialData = getDollarInfo("oficial");
        DollarApiResponse blueData = getDollarInfo("blue");
        if(Optional.ofNullable(officialData).isEmpty() || Optional.ofNullable(blueData).isEmpty())
            throw new MessageException("No se obtuvo resultado buscando datos");

//        return DollarDataResponse.builder()
//                .officialName(officialData.getNombre())
//                .officialBuyPrice(officialData.getCompra())
//                .officialSellPrice(officialData.getVenta())
//                .officialUpdated(officialData.getFechaActualizacion())
//                .blueName(blueData.getNombre())
//                .blueBuyPrice(blueData.getCompra())
//                .blueSellPrice(blueData.getVenta())
//                .blueUpdated(blueData.getFechaActualizacion())
//                .build();

        return mapper.dollarApiResponsesToDollarDataResponse(blueData, officialData);
    }

    @Override
    public DollarModel saveDollarData(String tipo) {

        DollarApiResponse dollarApiResponse = getDollarInfo(tipo);

        DollarModel dollarToSave = mapper.dollarApiResponseToDollarModel(dollarApiResponse);
        dollarToSave.setTipo(tipo);

        repository.save(dollarToSave);

        return dollarToSave;
    }

    private DollarApiResponse getDollarInfo(String type){
        return dollarApi.getDollarInfo(type);
    }

}
