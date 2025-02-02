package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
    Optional<SeatType> findByName(String name);
}
