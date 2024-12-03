package com.example.softravelbackend.controller;

import com.example.softravelbackend.model.Liquidation;
import com.example.softravelbackend.model.LiquidationRequest;
import com.example.softravelbackend.service.LiquidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/liquidations")
public class LiquidationController extends AbstractController {

    @Autowired
    private LiquidationService liquidationService;

    // Endpoint to create a new liquidation
    @PostMapping("/create")
    public ResponseEntity<Liquidation> createLiquidation(@RequestBody LiquidationRequest request) {
        Liquidation liquidation = liquidationService.createLiquidation(
                request.getUserId(),
                request.getMissionId(),
                request.getTrainCost(),
                request.getBusCost(),
                request.getTaxiCost(),
                request.getOtherTransportCost(),
                request.getInternetPackageCost(),
                request.getSimCardCost(),
                request.getHotelCost()
        );
        return new ResponseEntity<>(liquidation, HttpStatus.CREATED);
    }

    // Endpoint to get all liquidations for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Liquidation>> getLiquidationsForUser(@PathVariable Long userId) {
        List<Liquidation> liquidations = liquidationService.getLiquidationsForUser(userId);
        return new ResponseEntity<>(liquidations, HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Liquidation>> getMyLiquidations() {
        Long currentUserCin = getCurrentAuthenticatedUserCin();
        List<Liquidation> liquidations = liquidationService.getLiquidationsForUser(currentUserCin);
        return new ResponseEntity<>(liquidations, HttpStatus.OK);
    }
    

    // Endpoint to get all liquidations for a mission
    @GetMapping("/mission/{missionId}")
    public ResponseEntity<List<Liquidation>> getLiquidationsForMission(@PathVariable Long missionId) {
        List<Liquidation> liquidations = liquidationService.getLiquidationsForMission(missionId);
        return new ResponseEntity<>(liquidations, HttpStatus.OK);
    }

    // Endpoint to get all liquidations for a manager
    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Liquidation>> getLiquidationsForManager(@PathVariable Long managerId) {
        List<Liquidation> liquidations = liquidationService.getLiquidationsForManager(managerId);
        return new ResponseEntity<>(liquidations, HttpStatus.OK);
    }
}
