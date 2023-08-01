package com.digitalworld.api5.services;

import com.digitalworld.api5.entity.ConversionEntity;
import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.mapper.ConversionMapper;
import com.digitalworld.api5.persistence.ConversionRepository;
import com.digitalworld.api5.requests.ConversionHistoryRequest;
import com.digitalworld.api5.requests.ConversionRequest;
import com.digitalworld.api5.responses.ConversionResponse;
import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.impl.ConversionService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ConversionServiceTest {

    @InjectMocks
    ConversionService service;

    @Mock
    ConversionMapper mapper;
    @Mock
    ConversionRepository repository;
    @Captor
    ArgumentCaptor<ConversionEntity> conversionCaptor;


    @Test
    void convertDollarToPesos_ok(){
        float coinPrice = 10;
        float officialBuyPrice = 21.5F;
        float officialSellPrice = 20.3F;
        float blueBuyPrice = 38.1F;
        float blueSellPrice = 36.4F;
        ConversionRequest request = ConversionRequest.builder()
                .coinName("Coin1")
                .coinPrice(coinPrice)
                .officialBuyPrice(officialBuyPrice)
                .officialSellPrice(officialSellPrice)
                .blueBuyPrice(blueBuyPrice)
                .blueSellPrice(blueSellPrice)
                .build();
        ConversionEntity entity = ConversionEntity.builder()
                .coinName(request.getCoinName())
                .coinPrice(request.getCoinPrice())
                .officialBuyPrice(request.getOfficialBuyPrice())
                .officialSellPrice(request.getOfficialSellPrice())
                .blueBuyPrice(request.getBlueBuyPrice())
                .blueSellPrice(request.getBlueSellPrice())
                .build();
        when(mapper.getConversionEntity(any(),any())).thenReturn(entity);
        ConversionResponse response = service.convertDollarsToPesos(request);
        Assert.notNull(response);
        Assert.isTrue(response.getOfficialBuyPrice() == (officialBuyPrice * coinPrice));
        Assert.isTrue(response.getOfficialSellPrice() == (officialSellPrice * coinPrice));
        Assert.isTrue(response.getBlueBuyPrice() == (blueBuyPrice * coinPrice));
        Assert.isTrue(response.getBlueSellPrice() == (blueSellPrice * coinPrice));
        verify(repository, times(1)).save(any(ConversionEntity.class));
        verify(repository).save(conversionCaptor.capture());
        entity = conversionCaptor.getValue();
        assertThat(entity.getCoinName().equalsIgnoreCase(request.getCoinName()));
    }

    @Test
    void getHistoricData_ok(){
        List<ConversionEntity> data = new ArrayList<>();
        data.add(ConversionEntity.builder().id(1).coinName("Coin1").build());
        data.add(ConversionEntity.builder().id(2).coinName("Coin2").build());
        when(repository.findByCoinNameContainingIgnoreCase(anyString())).thenReturn(data);
        List<ConversionEntity> response = service.getHistoricData(ConversionHistoryRequest.builder().coinName("").build());
        Assert.notNull(response);
        Assert.notEmpty(response);
        Assert.isTrue(response.get(0).getId()==1);
        Assert.isTrue(response.get(1).getId()==2);
        verify(repository, times(1)).findByCoinNameContainingIgnoreCase(anyString());
    }
    @Test
    void getHistoricData_empty(){
        List<ConversionEntity> data = new ArrayList<>();
        when(repository.findByCoinNameContainingIgnoreCase(anyString())).thenReturn(data);
        List<ConversionEntity> response = service.getHistoricData(ConversionHistoryRequest.builder().coinName("").build());
        Assert.notNull(response);
        Assert.isTrue(response.isEmpty());
        verify(repository, times(1)).findByCoinNameContainingIgnoreCase(anyString());
    }
    @Test
    void getHistoricData_null(){
        when(repository.findByCoinNameContainingIgnoreCase(anyString())).thenReturn(null);
        List<ConversionEntity> response = service.getHistoricData(ConversionHistoryRequest.builder().coinName("").build());
        Assert.isNull(response);
        verify(repository, times(1)).findByCoinNameContainingIgnoreCase(anyString());
    }

    @Test
    void getConversionData_ok(){
        int conversionId = 1;
        ConversionEntity entity = ConversionEntity.builder().id(conversionId).coinName("Coin1").build();
        when(repository.findById(any(int.class))).thenReturn(Optional.of(entity));
        ConversionEntity response = service.getConversionData(conversionId);
        verify(repository, times(1)).findById(any());
    }

    @Test
    void updateConversionData_ok(){
        int conversionId = 1;
        String coinName = "Coin1";
        ConversionEntity entity = ConversionEntity.builder().id(conversionId).coinName(coinName).build();
        service.updateConversionData(entity);
        verify(repository, times(1)).save(any(ConversionEntity.class));
        verify(repository).save(conversionCaptor.capture());
        entity = conversionCaptor.getValue();
        assertThat(entity.getId()==conversionId);
        assertThat(entity.getCoinName().equalsIgnoreCase(coinName));
    }

    @Test
    void deleteConversion_ok(){
        int conversionId = 1;
        service.deleteConversion(conversionId);
        verify(repository, times(1)).deleteById(any());
    }

}
