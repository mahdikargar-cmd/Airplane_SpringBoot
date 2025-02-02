package com.Airplane.AirplaneApp.DTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SeatTypeDTO {
    private Long seatTypeId;
    private String name; // ECONOMY, BUSINESS, FIRST
    private String description;
    private BigDecimal basePrice;
    private Integer extraLegroom;
    private Boolean hasSpecialMeal;
    private Boolean hasPriorityBoarding;
    private Boolean hasLoungeAccess;
}
