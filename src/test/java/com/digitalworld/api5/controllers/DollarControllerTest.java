package com.digitalworld.api5.controllers;

import com.digitalworld.api5.responses.DollarDataResponse;
import com.digitalworld.api5.services.impl.DollarService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DollarController.class)
public class DollarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DollarService mockedService;

    @Test
    void dollarData_ok() throws Exception{
        DollarDataResponse response = DollarDataResponse.builder()
                .officialName("Dolar Oficial")
                .officialBuyPrice(20)
                .officialSellPrice(19)
                .blueName("Dolar Blue")
                .blueBuyPrice(30)
                .blueSellPrice(29)
                .build();
        when(mockedService.getDollarData()).thenReturn(response);
        this.mockMvc.perform(get("/dollar/data"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.officialName").isString())
                .andExpect(jsonPath("$.officialBuyPrice").isNumber())
                .andExpect(jsonPath("$.officialSellPrice").isNumber())
                .andExpect(jsonPath("$.blueName").isString())
                .andExpect(jsonPath("$.blueBuyPrice").isNumber())
                .andExpect(jsonPath("$.blueSellPrice").isNumber());
        verify(mockedService, times(1)).getDollarData();
    }


}
