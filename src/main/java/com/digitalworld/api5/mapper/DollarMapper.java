package com.digitalworld.api5.mapper;

import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.model.DollarApiModel;
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
    DollarDataResponse dollarApiResponsesToDollarDataResponse(DollarApiModel dollarBlue, DollarApiModel dollarOficial);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipo", source = "dollarApiModel.nombre")
    DollarEntity dollarApiResponseToDollarModel(DollarApiModel dollarApiModel);
}
