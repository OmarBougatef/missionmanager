package com.example.softravelbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String destination;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private Double budget;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @NotNull
    private Long userInfoCin;

    @ManyToOne
    @JoinColumn(name = "user_cin", nullable = false, referencedColumnName = "cin")
    @JsonBackReference
    private UserInfo userInfo;

    // Constructors, getters and setters

    public Mission() {
    }

    public Mission(String title, String description, String destination, LocalDate startDate, LocalDate endDate, Double budget, MissionStatus status, UserInfo userInfo) {
        this.title = title;
        this.description = description;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.status = status;
        this.userInfo = userInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public Long getUserInfoCin() {
        return userInfoCin;
    }

    public void setUserInfoCin(Long userInfoCin) {
        this.userInfoCin = userInfoCin;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
