package com.digitalworld.api5.services.impl;

import com.digitalworld.api5.entity.ConversionEntity;
import com.digitalworld.api5.mapper.ConversionMapper;
import com.digitalworld.api5.persistence.ConversionRepository;
import com.digitalworld.api5.requests.ConversionHistoryRequest;
import com.digitalworld.api5.requests.ConversionRequest;
import com.digitalworld.api5.responses.ConversionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversionService {

    private final ConversionMapper mapper;
    private final ConversionRepository repository;

    public ConversionResponse convertDollarsToPesos(ConversionRequest request){
        request.setBlueBuyPrice(request.getBlueBuyPrice() * request.getCoinPrice());
        request.setBlueSellPrice(request.getBlueSellPrice() * request.getCoinPrice());
        request.setOfficialBuyPrice(request.getOfficialBuyPrice() * request.getCoinPrice());
        request.setOfficialSellPrice(request.getOfficialSellPrice() * request.getCoinPrice());
        repository.save(mapper.getConversionEntity(request, LocalDateTime.now()));
        return ConversionResponse.builder()
                .officialBuyPrice(request.getOfficialBuyPrice())
                .officialSellPrice(request.getOfficialSellPrice())
                .blueBuyPrice(request.getBlueBuyPrice())
                .blueSellPrice(request.getBlueSellPrice())
                .build();
    }

    public List<ConversionEntity> getHistoricData(ConversionHistoryRequest request){
        return repository.findByCoinNameContainingIgnoreCase(request.getCoinName());
    }
    public ConversionEntity getConversionData(int id){
        return repository.findById(id).orElseThrow();
    }
    public void updateConversionData(ConversionEntity data){
        repository.save(data);
    }
    public void deleteConversion(int id){
        repository.deleteById(id);
    }

}
