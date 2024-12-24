package com.example.softravelbackend.model;

public class LiquidationRequest {

    private Long userId;
    private Long missionId;
    private Double trainCost;
    private Double busCost;
    private Double taxiCost;
    private Double otherTransportCost;
    private Double internetPackageCost;
    private Double simCardCost;
    private Double hotelCost;

    // New fields
    private LiquidationStatus status;  // Added status field
    private String remarks;            // Added remarks field

    // Default constructor
    public LiquidationRequest() {
    }

    // Constructor with new fields added
    public LiquidationRequest(Long userId, Long missionId, Double trainCost, Double busCost, Double taxiCost,
                              Double otherTransportCost, Double internetPackageCost, Double simCardCost,
                              Double hotelCost, LiquidationStatus status, String remarks) {
        this.userId = userId;
        this.missionId = missionId;
        this.trainCost = trainCost;
        this.busCost = busCost;
        this.taxiCost = taxiCost;
        this.otherTransportCost = otherTransportCost;
        this.internetPackageCost = internetPackageCost;
        this.simCardCost = simCardCost;
        this.hotelCost = hotelCost;
        this.status = status;   // Initialize the status field
        this.remarks = remarks; // Initialize the remarks field
    }

    // Getters and setters for all fields
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Double getTrainCost() {
        return trainCost;
    }

    public void setTrainCost(Double trainCost) {
        this.trainCost = trainCost;
    }

    public Double getBusCost() {
        return busCost;
    }

    public void setBusCost(Double busCost) {
        this.busCost = busCost;
    }

    public Double getTaxiCost() {
        return taxiCost;
    }

    public void setTaxiCost(Double taxiCost) {
        this.taxiCost = taxiCost;
    }

    public Double getOtherTransportCost() {
        return otherTransportCost;
    }

    public void setOtherTransportCost(Double otherTransportCost) {
        this.otherTransportCost = otherTransportCost;
    }

    public Double getInternetPackageCost() {
        return internetPackageCost;
    }

    public void setInternetPackageCost(Double internetPackageCost) {
        this.internetPackageCost = internetPackageCost;
    }

    public Double getSimCardCost() {
        return simCardCost;
    }

    public void setSimCardCost(Double simCardCost) {
        this.simCardCost = simCardCost;
    }

    public Double getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(Double hotelCost) {
        this.hotelCost = hotelCost;
    }

    // Getter and setter for the status field
    public LiquidationStatus getStatus() {
        return status;
    }

    public void setStatus(LiquidationStatus status) {
        this.status = status;
    }

    // Getter and setter for the remarks field
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
