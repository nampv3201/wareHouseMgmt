package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.PartnerDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.PartnerSearchRequest;
import com.datn.warehousemgmt.entities.Partner;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.mapper.PartnerMapper;
import com.datn.warehousemgmt.repository.PartnerRepository;
import com.datn.warehousemgmt.service.PartnerService;
import com.datn.warehousemgmt.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    @Override
    public Partner createPartner(PartnerDTO dto) {
        return null;
    }

    @Override
    public ServiceResponse getPartnerList(String name) {
        try{
            return new ServiceResponse(partnerRepository.findPartnerList(name),
                    "Lấy danh sách nhà cung cấp thành công",
                    200);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Partner updatePartner(PartnerDTO dto) {
        return null;
    }

    @Override
    public Partner deletePartner(PartnerDTO dto) {
        return null;
    }

    @Override
    public ServiceResponse findPartnerList(PartnerSearchRequest request) {
        try{
            Pageable pageable = PageUtils.customPage(request.getPageDTO());
            if(request.getType().equals("ALL")){
                request.setType(null);
            }
            Page<Partner> page = partnerRepository.findPartner(request.getSearch(),
                    request.getType(),
                    pageable);

            List<PartnerDTO> res = page.getContent().stream().map(partnerMapper::entityToPartnerDTO)
                    .toList();
            return new ServiceResponse(res,
                    "Lấy danh sách nhà cung cấp thành công",
                    200,
                    page.getTotalPages(),
                    page.getTotalElements(),
                    page.getNumber() + 1);
        }catch (IllegalArgumentException e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
