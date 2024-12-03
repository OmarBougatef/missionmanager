package com.example.softravelbackend.repository;

import com.example.softravelbackend.model.Mission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByUserInfoCin(Long cin);
    List<Mission> findByUserInfo_Manager_Cin(Long managerCin);
}
