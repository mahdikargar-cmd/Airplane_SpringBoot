package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.Aircraft;
import com.Airplane.AirplaneApp.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByAircraft(Aircraft aircraft);

    List<Seat> findByFlight_FlightId(Long flightId);

    boolean existsByFlight_FlightIdAndSeatNumberAndAvailableFalse(Long flightId, String seatNumber);

    List<Seat> findByFlight_FlightIdAndAvailable(Long flightId, boolean available);

    Optional<Seat> findByFlight_FlightIdAndSeatNumber(Long flightId, String seatNumber);

    // New method to check seat availability
    boolean existsByFlight_FlightIdAndSeatNumberAndAvailableTrue(Long flightId, String seatNumber);
}