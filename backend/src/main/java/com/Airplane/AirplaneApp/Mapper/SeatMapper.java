package com.Airplane.AirplaneApp.Mapper;

import com.Airplane.AirplaneApp.DTO.SeatDTO;
import com.Airplane.AirplaneApp.Entity.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    public SeatDTO toDTO(Seat seat) {
        return SeatDTO.builder()
                .seatNumber(seat.getSeatNumber())
                .available(seat.isAvailable())
                .seatType(seat.getSeatType())
                .position(seat.getPosition())
                .rowNumber(seat.getRowNumber())
                .columnLetter(seat.getColumnLetter())
                .build();
    }

    public Seat toEntity(SeatDTO dto) {
        Seat seat = new Seat();
        seat.setSeatNumber(dto.getSeatNumber());
        seat.setAvailable(dto.isAvailable());
        seat.setSeatType(dto.getSeatType());

        // مقداردهی position برای جلوگیری از خطای not-null
        seat.setPosition(dto.getPosition() != null ? dto.getPosition() : "UNKNOWN");
        seat.setStatus("AVAILABLE");
        seat.setRowNumber(dto.getRowNumber());
        seat.setColumnLetter(dto.getColumnLetter());

        return seat;
    }

}