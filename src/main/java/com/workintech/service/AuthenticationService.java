package com.workintech.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workintech.dao.MemberRepository;
import com.workintech.dao.RoleRepository;
import com.workintech.entity.Member;
import com.workintech.entity.Role;

@Service
public class AuthenticationService {
    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(String email, String password){
        Optional<Member> found = memberRepository.findByEmail(email);
        if(found.isPresent()){
            throw new RuntimeException("This email address is already registered.");
        }
        String encodedPw = passwordEncoder.encode(password);

        Role role = roleRepository.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Member registered = new Member();
        registered.setEmail(email);
        registered.setPassword(encodedPw);
        registered.setRoles(roles);

        return memberRepository.save(registered);
    }
}
