package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query(value = "SELECT DISTINCT m FROM Partner m " +
            "WHERE ?1 IS NULL OR ?1 = '' " +
            "OR m.name LIKE concat('%', coalesce(?1, ''), '%' ) " +
            "AND m.type = 'SUPPLIER'")
    List<Partner> findPartnerList(String name);

    @Query(value = "SELECT DISTINCT m FROM Partner m " +
            "WHERE (?1 IS NULL OR ?1 = '' " +
            "OR m.name LIKE concat('%', coalesce(?1, ''), '%' ) " +
            "OR m.phone LIKE concat('%', coalesce(?1, ''), '%' )) " +
            "AND (?2 IS NULL OR ?2 = '' OR " +
            "m.type = ?2)")
    Page<Partner> findPartner(String search,
                              String type,
                              Pageable pageable);
}
