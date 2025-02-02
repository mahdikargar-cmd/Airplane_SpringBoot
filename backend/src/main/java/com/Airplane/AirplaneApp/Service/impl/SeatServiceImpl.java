package com.Airplane.AirplaneApp.Service.impl;

import com.Airplane.AirplaneApp.DTO.SeatDTO;
import com.Airplane.AirplaneApp.Entity.Seat;
import com.Airplane.AirplaneApp.Exception.ResourceNotFoundException;
import com.Airplane.AirplaneApp.Mapper.SeatMapper;
import com.Airplane.AirplaneApp.Repository.SeatRepository;
import com.Airplane.AirplaneApp.Service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SeatDTO> getSeatsForFlight(Long flightId) {
        List<Seat> seats = seatRepository.findByFlight_FlightId(flightId);
        return seats.stream()
                .map(seatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeatDTO> getAvailableSeatsForFlight(Long flightId) {
        List<Seat> availableSeats = seatRepository.findByFlight_FlightIdAndAvailable(flightId, true);
        return availableSeats.stream()
                .map(seatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SeatDTO bookSeat(Long flightId, String seatNumber) {
        Seat seat = seatRepository.findByFlight_FlightIdAndSeatNumber(flightId, seatNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + seatNumber));

        if (!seat.isAvailable()) {
            throw new IllegalStateException("This seat is already booked!");
        }

        seat.setAvailable(false);
        seat = seatRepository.save(seat);
        return seatMapper.toDTO(seat);
    }

    @Override
    @Transactional
    public SeatDTO updateSeatAvailability(Long flightId, String seatNumber, boolean available) {
        Seat seat = seatRepository.findByFlight_FlightIdAndSeatNumber(flightId, seatNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + seatNumber));

        if (seat.isAvailable() == available) {
            throw new IllegalStateException("Seat is already in the desired state!");
        }

        seat.setAvailable(available);
        seat = seatRepository.save(seat);
        return seatMapper.toDTO(seat);
    }
}