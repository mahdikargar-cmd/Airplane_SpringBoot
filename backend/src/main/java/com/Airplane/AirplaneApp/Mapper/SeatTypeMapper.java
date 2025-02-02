package com.Airplane.AirplaneApp.Mapper;

import com.Airplane.AirplaneApp.DTO.SeatTypeDTO;
import com.Airplane.AirplaneApp.Entity.SeatType;

public class SeatTypeMapper {
    public static SeatTypeDTO mapToSeatTypeDTO(SeatType seatType) {
        SeatTypeDTO seatTypeDTO = new SeatTypeDTO();
        seatTypeDTO.setSeatTypeId(seatType.getSeatTypeId());
        seatTypeDTO.setName(seatType.getName());
        seatTypeDTO.setDescription(seatType.getDescription());
        seatTypeDTO.setBasePrice(seatType.getBasePrice());
        seatTypeDTO.setExtraLegroom(seatType.getExtraLegroom());
        seatTypeDTO.setHasSpecialMeal(seatType.getHasSpecialMeal());
        seatTypeDTO.setHasPriorityBoarding(seatType.getHasPriorityBoarding());
        seatTypeDTO.setHasLoungeAccess(seatType.getHasLoungeAccess());
        return seatTypeDTO;
    }

    public static SeatType mapToSeatTypeEntity(SeatTypeDTO seatTypeDTO) {
        SeatType seatType = new SeatType();
        seatType.setSeatTypeId(seatTypeDTO.getSeatTypeId());
        seatType.setName(seatTypeDTO.getName());
        seatType.setDescription(seatTypeDTO.getDescription());
        seatType.setBasePrice(seatTypeDTO.getBasePrice());
        seatType.setExtraLegroom(seatTypeDTO.getExtraLegroom());
        seatType.setHasSpecialMeal(seatTypeDTO.getHasSpecialMeal());
        seatType.setHasPriorityBoarding(seatTypeDTO.getHasPriorityBoarding());
        seatType.setHasLoungeAccess(seatTypeDTO.getHasLoungeAccess());
        return seatType;
    }
}
