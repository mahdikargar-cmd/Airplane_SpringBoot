package com.Airplane.AirplaneApp.Service;

import com.Airplane.AirplaneApp.DTO.SeatDTO;
import java.util.List;

public interface SeatService {
    List<SeatDTO> getSeatsForFlight(Long flightId);
    List<SeatDTO> getAvailableSeatsForFlight(Long flightId);
    SeatDTO updateSeatAvailability(Long flightId, String seatNumber, boolean available);
    SeatDTO bookSeat(Long flightId, String seatNumber);

}