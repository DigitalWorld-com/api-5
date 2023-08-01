package com.digitalworld.api5.services;

import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.integrations.DollarApiIntegration;
import com.digitalworld.api5.mapper.DollarMapper;
import com.digitalworld.api5.model.DollarApiModel;
import com.digitalworld.api5.persistence.DollarRepository;
import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.impl.DollarService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DollarServiceTest {

    @InjectMocks
    DollarService service;

    @Mock
    DollarApiIntegration dollarApi;
    @Mock
    DollarMapper dollarMapper;
    @Mock
    DollarRepository dollarRepository;


    @Test
    void dollarData_ok(){
        float officialBuyPrice = 21.5F;
        float officialSellPrice = 20.3F;
        float blueBuyPrice = 38.1F;
        float blueSellPrice = 36.4F;
        DollarApiModel dollarModel = DollarApiModel.builder().build();
        when(dollarApi.getDollarInfo(anyString())).thenReturn(dollarModel);
        when(dollarMapper.dollarApiResponseToDollarModel(any(),any())).thenReturn(new DollarEntity());
        when(dollarRepository.save(any(DollarEntity.class))).thenReturn(new DollarEntity());
        when(dollarMapper.dollarApiResponsesToDollarDataResponse(any(DollarApiModel.class), any(DollarApiModel.class)))
                .thenReturn(DollarDataResponse.builder()
                        .officialName("Dolar Oficial")
                        .officialBuyPrice(officialBuyPrice)
                        .officialSellPrice(officialSellPrice)
                        .blueName("Dolar Blue")
                        .blueBuyPrice(blueBuyPrice)
                        .blueSellPrice(blueSellPrice)
                        .build());
        DollarDataResponse response = service.getDollarData();
        Assert.notNull(response);
        Assert.isTrue(response.getOfficialBuyPrice() == officialBuyPrice);
        Assert.isTrue(response.getOfficialSellPrice() == officialSellPrice);
        Assert.isTrue(response.getBlueBuyPrice() == blueBuyPrice);
        Assert.isTrue(response.getBlueSellPrice() == blueSellPrice);
        verify(dollarRepository, times(2)).save(any(DollarEntity.class));
    }

}
