package com.example.softravelbackend.service;

import com.example.softravelbackend.model.*;
import com.example.softravelbackend.repository.LiquidationRepository;
import com.example.softravelbackend.repository.MissionRepository;
import com.example.softravelbackend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Liquidation createLiquidation(Long userId, Long missionId, double trainCost, double busCost, double taxiCost, double otherTransportCost, double internetPackageCost, double simCardCost, double hotelCost, LiquidationStatus status, String remarks) {
        // Fetch the UserInfo and Mission from the repositories
        UserInfo user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("Mission not found"));

        // Create a new Liquidation object with the provided parameters
        Liquidation liquidation = new Liquidation(user, mission, trainCost, busCost, taxiCost, otherTransportCost, internetPackageCost, simCardCost, hotelCost, status, remarks);

        // Save the new Liquidation object to the repository
        return liquidationRepository.save(liquidation);
    }

    public List<Liquidation> getAllLiquidation() {
        return liquidationRepository.findAll();
    }

    public Liquidation updateLiquidation(Long id, LiquidationRequest request) {
        // Find the existing Liquidation by ID or throw an exception if not found
        Liquidation liquidation = liquidationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liquidation not found"));

        // Fetch the UserInfo and Mission from the repositories based on the provided userId and missionId
        UserInfo user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new RuntimeException("Mission not found"));

        // Update the fields of the existing Liquidation object with values from the request
        liquidation.setUser(user);
        liquidation.setMission(mission);
        liquidation.setTrainCost(request.getTrainCost());
        liquidation.setBusCost(request.getBusCost());
        liquidation.setTaxiCost(request.getTaxiCost());
        liquidation.setOtherTransportCost(request.getOtherTransportCost());
        liquidation.setInternetPackageCost(request.getInternetPackageCost());
        liquidation.setSimCardCost(request.getSimCardCost());
        liquidation.setHotelCost(request.getHotelCost());
        liquidation.setTotalAmount(
                request.getTrainCost() + request.getBusCost() + request.getTaxiCost() + request.getOtherTransportCost() +
                        request.getInternetPackageCost() + request.getSimCardCost() + request.getHotelCost()
        ); // Recalculate totalAmount based on the costs
        liquidation.setDate(new Date());  // Optionally, set a new date for the update
        liquidation.setStatus(request.getStatus());  // Set the new status
        liquidation.setRemarks(request.getRemarks());  // Set the new remarks

        // Save the updated Liquidation back to the repository
        return liquidationRepository.save(liquidation);
    }

    public Liquidation validateLiquidation(Long id) {
        // Find the existing Liquidation by ID or throw an exception if not found
        Liquidation liquidation = liquidationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liquidation not found"));

        // Set the status to VALIDATED
        liquidation.setStatus(LiquidationStatus.VALIDATED);

        // Save the updated Liquidation back to the repository
        return liquidationRepository.save(liquidation);
    }

    public Liquidation refuseLiquidation(Long id) {
        // Find the existing Liquidation by ID or throw an exception if not found
        Liquidation liquidation = liquidationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liquidation not found"));

        // Set the status to REFUSED
        liquidation.setStatus(LiquidationStatus.REFUSED);

        // Save the updated Liquidation back to the repository
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
