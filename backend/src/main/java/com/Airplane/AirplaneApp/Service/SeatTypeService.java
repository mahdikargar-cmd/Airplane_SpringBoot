package com.Airplane.AirplaneApp.Service;

import com.Airplane.AirplaneApp.DTO.SeatTypeDTO;
import java.util.List;

public interface SeatTypeService {
    SeatTypeDTO createSeatType(SeatTypeDTO seatTypeDTO);
    SeatTypeDTO getSeatTypeById(Long id);
    List<SeatTypeDTO> getAllSeatTypes();
    SeatTypeDTO updateSeatType(SeatTypeDTO seatTypeDTO);
    void deleteSeatType(Long id);
}