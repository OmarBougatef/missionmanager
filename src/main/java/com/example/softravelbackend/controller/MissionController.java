package com.example.softravelbackend.controller;

import com.example.softravelbackend.model.Mission;
import com.example.softravelbackend.model.UserInfo;
import com.example.softravelbackend.repository.UserInfoRepository;
import com.example.softravelbackend.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private UserInfoRepository userInfoRepository;


    @GetMapping
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
    
    @GetMapping("/user/{cin}")
    public List<Mission> getMissionsByUser(@PathVariable Long cin) {
        return missionService.getMissionsByUser(cin);
    }

    @GetMapping("/manager/{managerCin}")
    public List<Mission> getMissionsByManager(@PathVariable Long managerCin) {
        return missionService.getMissionsByManager(managerCin);
    }
    
}
