package com.example.softravelbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserInfo {

    @Id
    @NotNull
    @Column(unique = true, nullable = false)
    private Long cin;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String passportNumber;

    @NotNull
    private LocalDate passportIssueDate;

    @NotNull
    private LocalDate passportExpiryDate;

    private String phoneNumber;
    private String address;

    @NotNull
    private LocalDate birthDate;

    private String nationality;
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Mission> missions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserInfo manager;

    // Constructors, getters, and setters
    public UserInfo() {}

    public UserInfo(Long cin, String email, String firstName, String lastName, String passportNumber,
                    LocalDate passportIssueDate, LocalDate passportExpiryDate, String phoneNumber,
                    String address, LocalDate birthDate, String nationality, String gender, Role role) {
        this.cin = cin;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.passportIssueDate = passportIssueDate;
        this.passportExpiryDate = passportExpiryDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
        this.role = role;
    }

    public Long getCin() { return cin; }

    public void setCin(Long cin) { this.cin = cin; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassportNumber() { return passportNumber; }

    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    public LocalDate getPassportIssueDate() { return passportIssueDate; }

    public void setPassportIssueDate(LocalDate passportIssueDate) { this.passportIssueDate = passportIssueDate; }

    public LocalDate getPassportExpiryDate() { return passportExpiryDate; }

    public void setPassportExpiryDate(LocalDate passportExpiryDate) { this.passportExpiryDate = passportExpiryDate; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Set<Mission> getMissions() { return missions; }

    public void setMissions(Set<Mission> missions) { this.missions = missions; }

    public UserInfo getManager() {
        return manager;
    }

    public void setManager(UserInfo manager) {
        this.manager = manager;
    }
}
