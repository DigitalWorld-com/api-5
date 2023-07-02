package com.digitalworld.api5.services;

import com.digitalworld.api5.entity.DollarEntity;
import com.digitalworld.api5.responses.DollarDataResponse;

public interface DollarServiceApi {

    DollarDataResponse getDollarData();

    DollarEntity saveDollarData(String tipo);

}
