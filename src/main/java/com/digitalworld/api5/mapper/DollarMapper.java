package com.digitalworld.api5.mapper;

import com.digitalworld.api5.model.DollarModel;
import com.digitalworld.api5.responses.DollarApiResponse;
import com.digitalworld.api5.responses.DollarDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DollarMapper {
    @Mapping(target = "officialUpdated", source = "dollarOficial.fechaActualizacion")
    @Mapping(target = "officialSellPrice", source = "dollarOficial.venta")
    @Mapping(target = "officialName", source = "dollarOficial.nombre")
    @Mapping(target = "officialBuyPrice", source = "dollarOficial.compra")
    @Mapping(target = "blueUpdated", source = "dollarBlue.fechaActualizacion")
    @Mapping(target = "blueSellPrice", source = "dollarBlue.venta")
    @Mapping(target = "blueName", source = "dollarBlue.nombre")
    @Mapping(target = "blueBuyPrice", source = "dollarBlue.compra")
    DollarDataResponse dollarApiResponsesToDollarDataResponse(DollarApiResponse dollarBlue, DollarApiResponse dollarOficial);


    @Mapping(target = "id", ignore = true)
    DollarModel dollarApiResponseToDollarModel(DollarApiResponse dollarApiResponse);
}
