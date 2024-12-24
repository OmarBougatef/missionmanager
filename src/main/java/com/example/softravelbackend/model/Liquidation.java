package com.example.softravelbackend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Liquidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    private double trainCost;
    private double busCost;
    private double taxiCost;
    private double otherTransportCost;

    private double internetPackageCost;
    private double simCardCost;

    private double hotelCost;

    private double totalAmount;

    @Temporal(TemporalType.DATE)
    private Date date;

    // New fields added
    @Enumerated(EnumType.STRING)  // Storing the enum as a string in the database
    private LiquidationStatus status;

    private String remarks;

    public Liquidation() {
    }

    public Liquidation(UserInfo user, Mission mission, double trainCost, double busCost, double taxiCost, double otherTransportCost, double internetPackageCost, double simCardCost, double hotelCost, LiquidationStatus status, String remarks) {
        this.user = user;
        this.mission = mission;
        this.trainCost = trainCost;
        this.busCost = busCost;
        this.taxiCost = taxiCost;
        this.otherTransportCost = otherTransportCost;
        this.internetPackageCost = internetPackageCost;
        this.simCardCost = simCardCost;
        this.hotelCost = hotelCost;
        this.totalAmount = trainCost + busCost + taxiCost + otherTransportCost + internetPackageCost + simCardCost + hotelCost;
        this.date = new Date();
        this.status = status;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public double getTrainCost() {
        return trainCost;
    }

    public void setTrainCost(double trainCost) {
        this.trainCost = trainCost;
    }

    public double getBusCost() {
        return busCost;
    }

    public void setBusCost(double busCost) {
        this.busCost = busCost;
    }

    public double getTaxiCost() {
        return taxiCost;
    }

    public void setTaxiCost(double taxiCost) {
        this.taxiCost = taxiCost;
    }

    public double getOtherTransportCost() {
        return otherTransportCost;
    }

    public void setOtherTransportCost(double otherTransportCost) {
        this.otherTransportCost = otherTransportCost;
    }

    public double getInternetPackageCost() {
        return internetPackageCost;
    }

    public void setInternetPackageCost(double internetPackageCost) {
        this.internetPackageCost = internetPackageCost;
    }

    public double getSimCardCost() {
        return simCardCost;
    }

    public void setSimCardCost(double simCardCost) {
        this.simCardCost = simCardCost;
    }

    public double getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(double hotelCost) {
        this.hotelCost = hotelCost;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // New getters and setters for the new fields
    public LiquidationStatus getStatus() {
        return status;
    }

    public void setStatus(LiquidationStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

