package com.example.softravelbackend.repository;

import com.example.softravelbackend.model.Liquidation;
import com.example.softravelbackend.model.Mission;
import com.example.softravelbackend.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiquidationRepository extends JpaRepository<Liquidation, Long> {
    List<Liquidation> findByUser(UserInfo user);

    List<Liquidation> findByMission(Mission mission);
}
