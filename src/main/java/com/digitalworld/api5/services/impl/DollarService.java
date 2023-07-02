package com.digitalworld.api5.services.impl;

import com.digitalworld.api5.exception.MessageException;
import com.digitalworld.api5.integrations.DollarApiIntegration;
import com.digitalworld.api5.mapper.DollarMapper;
import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.model.DollarApiModel;
import com.digitalworld.api5.persistence.DollarRepository;
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
        DollarApiModel officialData = getDollarInfo("oficial");
        DollarApiModel blueData = getDollarInfo("blue");
        if(Optional.ofNullable(officialData).isEmpty() || Optional.ofNullable(blueData).isEmpty())
            throw new MessageException("No se obtuvo resultado buscando datos");
        repository.save(mapper.dollarApiResponseToDollarModel(officialData));
        repository.save(mapper.dollarApiResponseToDollarModel(blueData));
        return mapper.dollarApiResponsesToDollarDataResponse(blueData, officialData);
    }

    @Override
    public DollarEntity saveDollarData(String tipo) {

        DollarApiModel dollarApiModel = getDollarInfo(tipo);

        DollarEntity dollarToSave = mapper.dollarApiResponseToDollarModel(dollarApiModel);
        dollarToSave.setTipo(tipo);

        repository.save(dollarToSave);

        return dollarToSave;
    }

    private DollarApiModel getDollarInfo(String type){
        return dollarApi.getDollarInfo(type);
    }

}
