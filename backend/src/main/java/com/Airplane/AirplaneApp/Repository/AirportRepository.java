package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCode(String code);
    boolean existsByCode(String code);
}