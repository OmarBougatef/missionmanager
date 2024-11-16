package com.example.softravelbackend.repository;

import com.example.softravelbackend.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByCin(Long cin);
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByCinAndEmail(Long cin, String email);

}
