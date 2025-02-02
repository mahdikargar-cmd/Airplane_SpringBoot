package com.Airplane.AirplaneApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "crew_type")
@Data
public class CrewType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crewTypeId;

    @Column(nullable = false, unique = true)
    private String code; // CAPT, FO, FA, etc.

    @Column(nullable = false)
    private String name; // Captain, First Officer, Flight Attendant, etc.

    @Column(nullable = false)
    private String category; // FLIGHT_DECK_CREW, CABIN_CREW

    private String description;

    @Column(nullable = false)
    private Integer requiredHoursOfRest;

    @Column(nullable = false)
    private Integer maxFlightHoursPerMonth;
}
