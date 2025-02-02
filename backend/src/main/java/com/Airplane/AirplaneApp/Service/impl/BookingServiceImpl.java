package com.Airplane.AirplaneApp.Service.impl;

import com.Airplane.AirplaneApp.DTO.BookingDTO;
import com.Airplane.AirplaneApp.Entity.*;
import com.Airplane.AirplaneApp.Exception.ResourceNotFoundException;
import com.Airplane.AirplaneApp.Mapper.BookingMapper;
import com.Airplane.AirplaneApp.Repository.BookingRepository;
import com.Airplane.AirplaneApp.Repository.FlightRepository;
import com.Airplane.AirplaneApp.Repository.SeatRepository;
import com.Airplane.AirplaneApp.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public Booking createBooking(BookingDTO bookingDTO) {
        Flight flight = flightRepository.findByFlightNumber(bookingDTO.getFlightNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with number: " + bookingDTO.getFlightNumber()));

        validateFlight(flight);

        // Find and validate the selected seat using the corrected method name
        Seat seat = seatRepository.findByFlight_FlightIdAndSeatNumber(
                Long.valueOf(flight.getAircraft().getRegistrationNumber()),
                bookingDTO.getSelectedSeat()
        ).orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + bookingDTO.getSelectedSeat()));

        validateSeat(seat);

        // Create and populate the booking using mapper
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setFlight(flight);
        booking.setStatus(BookingStatus.CONFIRMED);

        // Save booking and update seat in a single transaction
        booking = bookingRepository.save(booking);

        seat.setAvailable(false);
        seatRepository.save(seat);

        return booking;
    }

    @Override
    @Transactional
    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));

        validateCancellation(booking);

        booking.setStatus(BookingStatus.CANCELLED);

        Seat seat = seatRepository.findByFlight_FlightIdAndSeatNumber(
                Long.valueOf(booking.getFlight().getAircraft().getRegistrationNumber()),
                booking.getSeatNumber()
        ).orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + booking.getSeatNumber()));

        seat.setAvailable(true);
        seatRepository.save(seat);

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getBookingsByNationalId(String nationalId) {
        return bookingRepository.findByNationalId(nationalId);
    }

    @Override
    @Transactional(readOnly = true)
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
    }

    private void validateFlight(Flight flight) {
        if (flight.getScheduledDepartureTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Cannot book a flight that has already departed");
        }
    }

    private void validateSeat(Seat seat) {
        if (!seat.isAvailable()) {
            throw new IllegalStateException("Selected seat is not available: " + seat.getSeatNumber());
        }
    }

    private void validateCancellation(Booking booking) {
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled.");
        }

        if (booking.getFlight().getScheduledDepartureTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Cannot cancel a booking for a flight that has already departed");
        }
    }
}