package com.example.softravelbackend.controller;

import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserInfoController  extends AbstractController {

    @Autowired
    private UserInfoService userInfoService;

    // Admin, Manager, Collaborateur can access this endpoint
    @GetMapping
    public List<UserInfo> getAllUsers() {
        return userInfoService.getAllUsers();
    }

    // Admin, Manager, Collaborateur can access this endpoint
    @GetMapping("/{cin}")
    public ResponseEntity<UserInfo> getUserByCin(@PathVariable Long cin) {
        Optional<UserInfo> userInfo = userInfoService.getUserByCin(cin);
        if (userInfo.isPresent()) {
            return ResponseEntity.ok(userInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Only Admin can create a user
    @PostMapping
    public UserInfo createUser(@RequestBody UserInfo userInfo) {
        return userInfoService.createUser(userInfo);
    }

    // Only Admin can update a user
    @PutMapping("/{cin}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable Long cin, @RequestBody UserInfo userInfoDetails) {
        UserInfo updatedUser = userInfoService.updateUser(cin, userInfoDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Only Admin can delete a user
    @DeleteMapping("/{cin}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long cin) {
        userInfoService.deleteUser(cin);
        return ResponseEntity.noContent().build();
    }
}
