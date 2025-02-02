package com.Airplane.AirplaneApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private String seatType;

    @Column(nullable = true)
    private String position;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer rowNumber;

    @Column(nullable = false)
    private char columnLetter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    // Sanitize string fields before setting them
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = sanitizeString(seatNumber);
    }

    public void setSeatType(String seatType) {
        this.seatType = sanitizeString(seatType);
    }

    public void setPosition(String position) {
        this.position = sanitizeString(position);
    }

    private String sanitizeString(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\0", ""); // Remove null bytes
    }
}