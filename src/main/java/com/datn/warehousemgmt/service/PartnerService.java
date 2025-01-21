package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.PartnerDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.PartnerSearchRequest;
import com.datn.warehousemgmt.entities.Partner;

public interface PartnerService {

    Partner createPartner(PartnerDTO dto);

    Partner updatePartner(PartnerDTO dto);

    Partner deletePartner(PartnerDTO dto);

    ServiceResponse getPartnerList(String name);

    ServiceResponse findPartnerList(PartnerSearchRequest request);
}
