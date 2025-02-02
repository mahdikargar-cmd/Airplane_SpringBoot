package com.Airplane.AirplaneApp.DTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
public class TicketDTO {
    private Long ticketId;
    private String ticketNumber;
    private Long flightId;
    private String passengerName;
    private String passengerNationalId;
    private String seatNumber;
    private String ticketClass; // ECONOMY, BUSINESS, FIRST
    private BigDecimal price;
    private LocalDateTime purchaseDateTime;
    private String status; // RESERVED, CONFIRMED, CANCELLED
    private Long departureAirportId;
    private Long arrivalAirportId;
    private Long aircraftId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}