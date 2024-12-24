package com.example.softravelbackend.controller;

import com.example.softravelbackend.model.Liquidation;
import com.example.softravelbackend.model.LiquidationRequest;
import com.example.softravelbackend.model.Mission;
import com.example.softravelbackend.service.LiquidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/liquidations")
public class LiquidationController extends AbstractController {

    @Autowired
    private LiquidationService liquidationService;

    // Endpoint to create a new liquidation
    @PostMapping("/create")
    public ResponseEntity<Liquidation> createLiquidation(@RequestBody LiquidationRequest request) {
        // Call the service to create a liquidation with the new parameters
        Liquidation liquidation = liquidationService.createLiquidation(
                request.getUserId(),
                request.getMissionId(),
                request.getTrainCost(),
                request.getBusCost(),
                request.getTaxiCost(),
                request.getOtherTransportCost(),
                request.getInternetPackageCost(),
                request.getSimCardCost(),
                request.getHotelCost(),
                request.getStatus(),   // Added status
                request.getRemarks()   // Added remarks
        );

        // Return the created liquidation with a CREATED status
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

    @GetMapping("/all")
    public List<Liquidation> getAllLiquidation() {
        return liquidationService.getAllLiquidation();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Liquidation> updateLiquidation(@PathVariable Long id, @RequestBody LiquidationRequest request) {
        // Call the service to update the liquidation with the new parameters from the request
        Liquidation updatedLiquidation = liquidationService.updateLiquidation(id, request);

        // Return the updated liquidation with a status of OK
        return ResponseEntity.ok(updatedLiquidation);
    }

    @PutMapping("/{id}/validate")
    public ResponseEntity<Liquidation> validateLiquidation(@PathVariable Long id) {
        // Call the service to validate the liquidation
        Liquidation validatedLiquidation = liquidationService.validateLiquidation(id);

        // Return the validated liquidation with a status of OK
        return ResponseEntity.ok(validatedLiquidation);
    }

    @PutMapping("/{id}/refuse")
    public ResponseEntity<Liquidation> refuseLiquidation(@PathVariable Long id) {
        // Call the service to refuse the liquidation
        Liquidation refusedLiquidation = liquidationService.refuseLiquidation(id);

        // Return the refused liquidation with a status of OK
        return ResponseEntity.ok(refusedLiquidation);
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
