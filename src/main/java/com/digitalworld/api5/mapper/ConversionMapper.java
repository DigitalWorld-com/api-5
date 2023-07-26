package com.digitalworld.api5.mapper;

import com.digitalworld.api5.entity.ConversionEntity;
import com.digitalworld.api5.requests.ConversionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper
public interface ConversionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coinName", source = "data.coinName")
    @Mapping(target = "coinPrice", source = "data.coinPrice")
    @Mapping(target = "officialBuyPrice", source = "data.officialBuyPrice")
    @Mapping(target = "officialSellPrice", source = "data.officialSellPrice")
    @Mapping(target = "blueBuyPrice", source = "data.blueBuyPrice")
    @Mapping(target = "blueSellPrice", source = "data.blueSellPrice")
    @Mapping(target = "dateCreated", source = "dateAdded")
    ConversionEntity getConversionEntity(ConversionRequest data, LocalDateTime dateAdded);
    
}
