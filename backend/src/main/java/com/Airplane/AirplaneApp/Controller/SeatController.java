package com.Airplane.AirplaneApp.Controller;

import com.Airplane.AirplaneApp.DTO.SeatDTO;
import com.Airplane.AirplaneApp.Entity.Seat;
import com.Airplane.AirplaneApp.Exception.ResourceNotFoundException;
import com.Airplane.AirplaneApp.Repository.SeatRepository;
import com.Airplane.AirplaneApp.Service.SeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<SeatDTO>> getSeatsForFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatService.getSeatsForFlight(flightId));
    }

    @PostMapping("/flight/{flightId}/seat/{seatNumber}/book")
    public ResponseEntity<SeatDTO> bookSeat(
            @PathVariable Long flightId,
            @PathVariable String seatNumber) {
        return ResponseEntity.ok(seatService.bookSeat(flightId, seatNumber));
    }

    @GetMapping("/flight/{flightId}/available")
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatService.getAvailableSeatsForFlight(flightId));
    }

    @PutMapping("/flight/{flightId}/seat/{seatNumber}")
    public ResponseEntity<SeatDTO> updateSeatAvailability(
            @PathVariable Long flightId,
            @PathVariable String seatNumber,
            @RequestParam boolean available) {
        return ResponseEntity.ok(seatService.updateSeatAvailability(flightId, seatNumber, available));
    }
}