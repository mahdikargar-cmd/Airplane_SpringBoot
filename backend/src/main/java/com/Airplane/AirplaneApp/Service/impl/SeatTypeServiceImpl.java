package com.Airplane.AirplaneApp.Service.impl;

import com.Airplane.AirplaneApp.DTO.SeatTypeDTO;
import com.Airplane.AirplaneApp.Entity.SeatType;
import com.Airplane.AirplaneApp.Mapper.SeatTypeMapper;
import com.Airplane.AirplaneApp.Repository.SeatTypeRepository;
import com.Airplane.AirplaneApp.Service.SeatTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeatTypeServiceImpl implements SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;

    @Override
    public SeatTypeDTO createSeatType(SeatTypeDTO seatTypeDTO) {
        SeatType seatType = SeatTypeMapper.mapToSeatTypeEntity(seatTypeDTO);
        seatType = seatTypeRepository.save(seatType);
        return SeatTypeMapper.mapToSeatTypeDTO(seatType);
    }

    @Override
    public SeatTypeDTO getSeatTypeById(Long id) {
        SeatType seatType = seatTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SeatType not found with id: " + id));
        return SeatTypeMapper.mapToSeatTypeDTO(seatType);
    }

    @Override
    public List<SeatTypeDTO> getAllSeatTypes() {
        List<SeatType> seatTypes = seatTypeRepository.findAll();
        return seatTypes.stream()
                .map(SeatTypeMapper::mapToSeatTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeatTypeDTO updateSeatType(SeatTypeDTO seatTypeDTO) {
        SeatType seatType = seatTypeRepository.findById(seatTypeDTO.getSeatTypeId())
                .orElseThrow(() -> new RuntimeException("SeatType not found with id: " + seatTypeDTO.getSeatTypeId()));
        updateSeatTypeEntityFromDTO(seatType, seatTypeDTO);
        SeatType updatedSeatType = seatTypeRepository.save(seatType);
        return SeatTypeMapper.mapToSeatTypeDTO(updatedSeatType);
    }

    @Override
    public void deleteSeatType(Long id) {
        seatTypeRepository.deleteById(id);
    }

    private void updateSeatTypeEntityFromDTO(SeatType seatType, SeatTypeDTO seatTypeDTO) {
        if (seatTypeDTO.getName() != null) {
            seatType.setName(seatTypeDTO.getName());
        }
        if (seatTypeDTO.getDescription() != null) {
            seatType.setDescription(seatTypeDTO.getDescription());
        }
        if (seatTypeDTO.getBasePrice() != null) {
            seatType.setBasePrice(seatTypeDTO.getBasePrice());
        }
        if (seatTypeDTO.getExtraLegroom() != null) {
            seatType.setExtraLegroom(seatTypeDTO.getExtraLegroom());
        }
        if (seatTypeDTO.getHasSpecialMeal() != null) {
            seatType.setHasSpecialMeal(seatTypeDTO.getHasSpecialMeal());
        }
        if (seatTypeDTO.getHasPriorityBoarding() != null) {
            seatType.setHasPriorityBoarding(seatTypeDTO.getHasPriorityBoarding());
        }
        if (seatTypeDTO.getHasLoungeAccess() != null) {
            seatType.setHasLoungeAccess(seatTypeDTO.getHasLoungeAccess());
        }
    }
}
