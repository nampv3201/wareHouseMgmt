package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.PacketDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;

public interface PacketService {

    ServiceResponse importPacket(PacketDTO dto);

    ServiceResponse exportPacket(String rfid);

    ServiceResponse updatePacket(String rfid);

    Boolean checkPacket(String rfid);
}
