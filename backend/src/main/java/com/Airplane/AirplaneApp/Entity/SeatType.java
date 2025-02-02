package com.Airplane.AirplaneApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "SeatType")
@Data
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatTypeId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column
    private Integer extraLegroom;

    @Column
    private Boolean hasSpecialMeal;

    @Column
    private Boolean hasPriorityBoarding;

    @Column
    private Boolean hasLoungeAccess;
}