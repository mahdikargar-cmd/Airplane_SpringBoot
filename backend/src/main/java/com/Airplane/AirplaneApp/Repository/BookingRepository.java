package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByNationalId(String nationalId);

}
