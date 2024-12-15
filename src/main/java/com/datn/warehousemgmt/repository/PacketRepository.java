package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacketRepository extends JpaRepository<Packet, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 1 THEN true ELSE false END " +
            "FROM Packet WHERE rfidTag = ?1")
    Boolean checkIfAlreadyImport(String rfidTag);
}
