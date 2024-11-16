package com.example.softravelbackend.service;

import com.example.softravelbackend.model.Mission;
import com.example.softravelbackend.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Optional<Mission> getMissionById(Long id) {
        return missionRepository.findById(id);
    }

    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Mission updateMission(Long id, Mission missionDetails) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new RuntimeException("Mission not found"));

        mission.setTitle(missionDetails.getTitle());
        mission.setDescription(missionDetails.getDescription());
        mission.setDestination(missionDetails.getDestination());
        mission.setStartDate(missionDetails.getStartDate());
        mission.setEndDate(missionDetails.getEndDate());
        mission.setBudget(missionDetails.getBudget());
        mission.setStatus(missionDetails.getStatus());

        return missionRepository.save(mission);
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}
