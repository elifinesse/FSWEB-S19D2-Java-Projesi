package com.workintech.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.workintech.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    
    @Query("SELECT m FROM Member m WHERE m.email=:email")
    Optional<Member> findByEmail(String email);
}
