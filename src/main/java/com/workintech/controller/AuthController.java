package com.workintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.dto.MemberResponse;
import com.workintech.entity.Member;
import com.workintech.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public MemberResponse register(@RequestBody Member member) {
        Member registered = authenticationService.register(member.getEmail(), member.getPassword());

        return new MemberResponse(registered.getId(), registered.getEmail(), registered.getPassword());
    }
    
}
