package com.datn.warehousemgmt.mapper;

import com.datn.warehousemgmt.dto.PartnerDTO;
import com.datn.warehousemgmt.entities.Partner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

    PartnerDTO entityToPartnerDTO(Partner partner);

    List<PartnerDTO> entitiesToPartnerDTOs(List<Partner> partners);
}
