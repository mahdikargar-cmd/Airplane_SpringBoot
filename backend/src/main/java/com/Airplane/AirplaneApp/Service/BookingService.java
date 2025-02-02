package com.Airplane.AirplaneApp.Service;

import com.Airplane.AirplaneApp.DTO.BookingDTO;
import com.Airplane.AirplaneApp.Entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(BookingDTO bookingDTO);
    Booking getBooking(Long id);
    List<Booking> getBookingsByNationalId(String nationalId);
    Booking cancelBooking(Long id);
}
