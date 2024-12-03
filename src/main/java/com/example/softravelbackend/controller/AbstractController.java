package com.example.softravelbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.repository.UserInfoRepository;

public abstract class AbstractController {
    
    @Autowired
    protected UserInfoRepository userInfoRepository;

    protected Long getCurrentAuthenticatedUserCin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = ((User) authentication.getPrincipal()).getUsername();
        UserInfo userInfo = userInfoRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userInfo.getCin();
    }
}
