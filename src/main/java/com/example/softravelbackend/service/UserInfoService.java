package com.example.softravelbackend.service;

import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * Load user by email (used for Spring Security authentication).
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return new org.springframework.security.core.userdetails.User(
                userInfo.getEmail(),
                String.valueOf(userInfo.getCin()), // Use CIN as password
                List.of(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole()))
        );
    }

    /**
     * Authenticate user using both CIN and email.
     */
    public UserInfo authenticateUser(Long cin, String email) {
        return userInfoRepository.findByCinAndEmail(cin, email)
                .orElseThrow(() -> new RuntimeException("Invalid CIN or email"));
    }

    /**
     * Get all users.
     */
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    /**
     * Get a user by CIN.
     */
    public Optional<UserInfo> getUserByCin(Long cin) {
        return userInfoRepository.findById(cin);
    }

    /**
     * Create a new user.
     */
    public UserInfo createUser(UserInfo userInfo) {
        if (userInfoRepository.existsById(userInfo.getCin()) ||
                userInfoRepository.findByEmail(userInfo.getEmail()).isPresent()) {
            throw new RuntimeException("User with the given CIN or email already exists");
        }
        return userInfoRepository.save(userInfo);
    }

    /**
     * Update an existing user.
     */
    @Transactional
    public UserInfo updateUser(Long cin, UserInfo userInfoDetails) {
        UserInfo existingUser = userInfoRepository.findById(cin)
                .orElseThrow(() -> new RuntimeException("User not found with CIN: " + cin));

        existingUser.setEmail(userInfoDetails.getEmail());
        existingUser.setFirstName(userInfoDetails.getFirstName());
        existingUser.setLastName(userInfoDetails.getLastName());
        existingUser.setPassportNumber(userInfoDetails.getPassportNumber());
        existingUser.setPassportIssueDate(userInfoDetails.getPassportIssueDate());
        existingUser.setPassportExpiryDate(userInfoDetails.getPassportExpiryDate());
        existingUser.setPhoneNumber(userInfoDetails.getPhoneNumber());
        existingUser.setAddress(userInfoDetails.getAddress());
        existingUser.setBirthDate(userInfoDetails.getBirthDate());
        existingUser.setNationality(userInfoDetails.getNationality());
        existingUser.setGender(userInfoDetails.getGender());
        existingUser.setRole(userInfoDetails.getRole());

        return userInfoRepository.save(existingUser);
    }

    /**
     * Delete a user by CIN.
     */
    public void deleteUser(Long cin) {
        if (!userInfoRepository.existsById(cin)) {
            throw new RuntimeException("User not found with CIN: " + cin);
        }
        userInfoRepository.deleteById(cin);
    }
}
