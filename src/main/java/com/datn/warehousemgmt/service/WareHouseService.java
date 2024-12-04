package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.WareHouseDTO;

public interface WareHouseService {

    ServiceResponse addNewWareHouse(WareHouseDTO dto);
    ServiceResponse updateWareHouse(WareHouseDTO dto);
    ServiceResponse deleteWareHouse(WareHouseDTO dto);
}
