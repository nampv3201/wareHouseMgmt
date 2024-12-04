package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.WareHouseDTO;
import com.datn.warehousemgmt.service.WareHouseService;

public class WareHouseServiceImpl implements WareHouseService {

    @Override
    public ServiceResponse addNewWareHouse(WareHouseDTO dto) {
        return null;
    }

    @Override
    public ServiceResponse updateWareHouse(WareHouseDTO dto) {
        return null;
    }

    @Override
    public ServiceResponse deleteWareHouse(WareHouseDTO dto) {
        return null;
    }

    public void validateDto(WareHouseDTO dto) {
        if(dto.getName() == null || dto.getName().isEmpty()) {

        }
    }
}
