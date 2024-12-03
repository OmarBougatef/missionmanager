package com.example.softravelbackend.service;

import com.example.softravelbackend.model.Liquidation;
import com.example.softravelbackend.model.Mission;
import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.repository.LiquidationRepository;
import com.example.softravelbackend.repository.MissionRepository;
import com.example.softravelbackend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiquidationService {

    @Autowired
    private LiquidationRepository liquidationRepository;

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private MissionRepository missionRepository;

    // Method to create a new liquidation
    public Liquidation createLiquidation(Long userId, Long missionId, double trainCost, double busCost, double taxiCost, double otherTransportCost, double internetPackageCost, double simCardCost, double hotelCost) {
        UserInfo user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("Mission not found"));

        Liquidation liquidation = new Liquidation(user, mission, trainCost, busCost, taxiCost, otherTransportCost, internetPackageCost, simCardCost, hotelCost);
        return liquidationRepository.save(liquidation);
    }

    // Method to get all liquidations for a user
    public List<Liquidation> getLiquidationsForUser(Long cin) {
        UserInfo user = userRepository.findByCin(cin).orElseThrow(() -> new RuntimeException("User not found"));
        return liquidationRepository.findByUser(user);
    }

    // Method to get all liquidations for a mission
    public List<Liquidation> getLiquidationsForMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("Mission not found"));
        return liquidationRepository.findByMission(mission);
    }

    public List<Liquidation> getLiquidationsForManager(Long managerId) {
        return liquidationRepository.findByUser_Manager_Cin(managerId);
    }
}
