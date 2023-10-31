package com.workintech.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.workintech.entity.Role;

public interface RoleRepository {
    
    @Query("SELECT r FROM Role r WHERE r.authority=:authority")
    Optional<Role> findByAuthority(String authority);
}
