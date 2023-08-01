package com.digitalworld.api5.controllers;

import com.digitalworld.api5.entity.ConversionEntity;
import com.digitalworld.api5.requests.ConversionRequest;
import com.digitalworld.api5.requests.ConversionHistoryRequest;
import com.digitalworld.api5.responses.ConversionResponse;
import com.digitalworld.api5.services.impl.ConversionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConversionController.class)
class ConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ConversionService mockedService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void dollarData_ok() throws Exception{
        ConversionRequest request = ConversionRequest.builder()
                .coinName("coin")
                .coinPrice(1)
                .officialBuyPrice(20)
                .officialSellPrice(19)
                .blueBuyPrice(30)
                .blueSellPrice(29)
                .build();
        ConversionResponse response = ConversionResponse.builder()
                .officialBuyPrice(20)
                .officialSellPrice(19)
                .blueBuyPrice(30)
                .blueSellPrice(29)
                .build();
        when(mockedService.convertDollarsToPesos(request)).thenReturn(response);
        this.mockMvc.perform(post("/convert/dollar-pesos", request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isOk());
        verify(mockedService, times(1)).convertDollarsToPesos(any(ConversionRequest.class));
    }

    @Test
    void allConversions_ok() throws Exception{
        List<ConversionEntity> data = new ArrayList<>();
        data.add(ConversionEntity.builder().id(1).coinName("Coin1").build());
        data.add(ConversionEntity.builder().id(2).coinName("Coin2").build());
        when(mockedService.getHistoricData(any(ConversionHistoryRequest.class))).thenReturn(data);
        MvcResult mvcResult = this.mockMvc.perform(get("/convert/allconversions?text="))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(mockedService, times(1)).getHistoricData(any(ConversionHistoryRequest.class));
    }

    @Test
    void getConversion_ok() throws Exception{
        int conversionId = 1;
        ConversionEntity entity = ConversionEntity.builder().id(conversionId).coinName("Coin1").build();
        when(mockedService.getConversionData(conversionId)).thenReturn(entity);
        MvcResult mvcResult = this.mockMvc.perform(get("/convert/conversion/" + conversionId))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(conversionId)).andReturn();
        verify(mockedService, times(1)).getConversionData(conversionId);
    }

    @Test
    void updateConversion_ok() throws Exception{
        ConversionEntity entity = ConversionEntity.builder().id(1).coinName("Coin1").build();
        mockMvc.perform(put("/convert/conversion/")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(entity)))
                .andExpect(status().is2xxSuccessful());
        verify(mockedService, times(1)).updateConversionData(any(ConversionEntity.class));
    }

    @Test
    void deleteConversion_ok() throws Exception{
        int deleteId = 1;
        mockMvc.perform(delete("/convert/delete/" + deleteId))
                .andExpect(status().is2xxSuccessful());
        verify(mockedService, times(1)).deleteConversion(deleteId);
    }
}
