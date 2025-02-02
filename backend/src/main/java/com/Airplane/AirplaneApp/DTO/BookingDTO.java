package com.Airplane.AirplaneApp.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String phoneNumber;
    private String selectedSeat;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal price;
}
