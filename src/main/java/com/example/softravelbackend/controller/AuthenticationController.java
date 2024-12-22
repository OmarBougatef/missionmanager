package com.example.softravelbackend.controller;

import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        UserInfo user = userInfoService.authenticateUser(authRequest.getCin(), authRequest.getEmail());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid CIN or email");
        }
    }

}

class AuthRequest {
    private Long cin;
    private String email;

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
