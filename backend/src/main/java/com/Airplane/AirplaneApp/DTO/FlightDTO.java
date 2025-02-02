package com.Airplane.AirplaneApp.DTO;

import com.Airplane.AirplaneApp.Entity.FlightStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long flightId;
    private String flightNumber;
    private Long aircraftId;
    @NotNull(message = "Departure Airport ID is required")
    private Long departureAirportId;

    @NotNull(message = "Arrival Airport ID is required")
    private Long arrivalAirportId;

    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime scheduledArrivalTime;
    private FlightStatus status;
    private BigDecimal basePrice;
    private String gate;
    private String remarks;
}
