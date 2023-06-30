package com.digitalworld.api5.services;

import com.digitalworld.api5.model.DollarModel;
import com.digitalworld.api5.responses.DollarDataResponse;

public interface DollarServiceApi {

    DollarDataResponse getDollarData();

    DollarModel saveDollarData(String tipo);

}
