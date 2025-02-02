package com.Airplane.AirplaneApp.Mapper;

import com.Airplane.AirplaneApp.DTO.BookingDTO;
import com.Airplane.AirplaneApp.Entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setFirstName(dto.getFirstName());
        booking.setLastName(dto.getLastName());
        booking.setNationalId(dto.getNationalId());
        booking.setPhoneNumber(dto.getPhoneNumber());
        booking.setSeatNumber(dto.getSelectedSeat());
        booking.setPrice(dto.getPrice());
        return booking;
    }

    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setFirstName(booking.getFirstName());
        dto.setLastName(booking.getLastName());
        dto.setNationalId(booking.getNationalId());
        dto.setPhoneNumber(booking.getPhoneNumber());
        dto.setSelectedSeat(booking.getSeatNumber());
        dto.setFlightNumber(booking.getFlight().getFlightNumber());
        dto.setDepartureTime(booking.getFlight().getScheduledDepartureTime());
        dto.setArrivalTime(booking.getFlight().getScheduledArrivalTime());
        dto.setPrice(booking.getPrice());
        return dto;
    }
}