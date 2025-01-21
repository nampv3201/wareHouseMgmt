package com.datn.warehousemgmt.dto.request;

import com.datn.warehousemgmt.utils.PageDTO;
import lombok.Data;

@Data
public class PartnerSearchRequest {
    private String search;
    private String type;
    private PageDTO pageDTO;
}
