package com.Airplane.AirplaneApp.DTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private String seatNumber;
    private boolean available;
    private String seatType;
    private String position;  // "LEFT" or "RIGHT"
    private Integer rowNumber;
    private char columnLetter; // Add this field
}
