package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.CrewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewTypeRepository extends JpaRepository<CrewType, Long> {
    CrewType findByCode(String code);
}
