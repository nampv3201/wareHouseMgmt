package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsernameAndStatus(String username, Boolean status);

}
