package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketNumber(String ticketNumber);
    List<Ticket> findByPassengerNationalId(String nationalId);

    @Query("SELECT t FROM Ticket t WHERE t.departureAirport.airportId = :departureId " +
            "AND t.arrivalAirport.airportId = :arrivalId " +
            "AND DATE(t.departureTime) = DATE(:departureTime) " +
            "AND t.status = 'CONFIRMED'")
    List<Ticket> findAvailableTickets(@Param("departureId") Long departureId,
                                      @Param("arrivalId") Long arrivalId,
                                      @Param("departureTime") LocalDateTime departureTime);
}