package com.example.softravelbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softravelbackend.model.Mission;
import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.repository.UserInfoRepository;
import com.example.softravelbackend.service.MissionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private UserInfoRepository userInfoRepository;


    @GetMapping("/all")
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMissionById(@PathVariable Long id) {
        Optional<Mission> mission = missionService.getMissionById(id);
        if (mission.isPresent()) {
            return ResponseEntity.ok(mission.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Mission createMission(@RequestBody Mission mission) {
        Long userCin = mission.getUserInfoCin();
        UserInfo userInfo = userInfoRepository.findById(userCin).orElseThrow(() -> new RuntimeException("User not found"));
        mission.setUserInfo(userInfo);
        return missionService.createMission(mission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mission> updateMission(@PathVariable Long id, @RequestBody Mission missionDetails) {
        Mission updatedMission = missionService.updateMission(id, missionDetails);
        return ResponseEntity.ok(updatedMission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
    

    @GetMapping("/my")
    public List<Mission> getMyMissions() {
        Long currentUserCin = getCurrentAuthenticatedUserCin();
        return missionService.getMissionsByUser(currentUserCin);
    }

    @GetMapping("/manager/{managerCin}")
    public List<Mission> getMissionsByManager(@PathVariable Long managerCin) {
        return missionService.getMissionsByManager(managerCin);
    }

    private Long getCurrentAuthenticatedUserCin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = ((User) authentication.getPrincipal()).getUsername();
        UserInfo userInfo = userInfoRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userInfo.getCin();
    }
    
}
