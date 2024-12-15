package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.PacketDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.entities.Packet;
import com.datn.warehousemgmt.repository.PacketRepository;
import com.datn.warehousemgmt.service.PacketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacketServiceImpl implements PacketService {

    private final PacketRepository packetRepository;
    @Override
    @Transactional
    public ServiceResponse importPacket(PacketDTO dto) {
        Packet packet = new Packet();
        BeanUtils.copyProperties(dto, packet);
        return new ServiceResponse(packetRepository.save(packet), "Nhập kho thành công", 200);
    }

    @Override
    public ServiceResponse exportPacket() {
        return null;
    }

    @Override
    public ServiceResponse updatePacket() {
        return null;
    }

    @Override
    public Boolean checkPacket(String rfid) {
        return packetRepository.checkIfAlreadyImport(rfid);
    }
}
