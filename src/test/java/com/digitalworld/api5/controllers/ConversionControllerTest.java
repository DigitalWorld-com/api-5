package com.digitalworld.api5.controllers;

import com.digitalworld.api5.requests.ConversionRequest;
import com.digitalworld.api5.responses.ConversionResponse;
import com.digitalworld.api5.services.impl.ConversionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConversionController.class)
public class ConversionControllerTest {

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
        //repository.save
        this.mockMvc.perform(post("/convert/dollar-pesos", request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isOk())
                /*.andExpect(jsonPath("$.officialBuyPrice").isNumber())
                .andExpect(jsonPath("$.officialSellPrice").isNumber())
                .andExpect(jsonPath("$.blueBuyPrice").isNumber())
                .andExpect(jsonPath("$.blueSellPrice").isNumber())
                .andExpect(jsonPath("$.officialBuyPrice").value(response.getOfficialBuyPrice()))
                .andExpect(jsonPath("$.officialSellPrice").value(response.getOfficialSellPrice()))
                .andExpect(jsonPath("$.blueBuyPrice").value(response.getBlueBuyPrice()))
                .andExpect(jsonPath("$.blueSellPrice").value(response.getBlueSellPrice()))*/;
        verify(mockedService, times(1)).convertDollarsToPesos(any(ConversionRequest.class));
    }

}
