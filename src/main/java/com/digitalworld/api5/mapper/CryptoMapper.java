package com.digitalworld.api5.mapper;
import com.digitalworld.api5.entity.CryptoEntity;
import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.model.DollarApiModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper
public interface CryptoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "dateChecked", source = "date")
    CryptoEntity dataToCryptoEntity(String name, String currency, double price, LocalDateTime date);
}
