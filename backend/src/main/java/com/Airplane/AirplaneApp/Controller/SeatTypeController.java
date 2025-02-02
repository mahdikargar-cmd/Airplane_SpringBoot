package com.Airplane.AirplaneApp.Controller;

import com.Airplane.AirplaneApp.DTO.SeatTypeDTO;
import com.Airplane.AirplaneApp.Service.SeatTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/seattype")
@AllArgsConstructor
@Tag(name = "Seat Type Management", description = "APIs for managing seat types")
public class SeatTypeController {

    private final SeatTypeService seatTypeService;

    @PostMapping
    @Operation(summary = "Create new seat type", description = "Create a new seat type with the provided details")
    public ResponseEntity<SeatTypeDTO> createSeatType(@RequestBody SeatTypeDTO seatTypeDTO) {
        SeatTypeDTO createdSeatType = seatTypeService.createSeatType(seatTypeDTO);
        return new ResponseEntity<>(createdSeatType, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all seat types", description = "Retrieve a list of all seat types")
    public ResponseEntity<List<SeatTypeDTO>> getAllSeatTypes() {
        List<SeatTypeDTO> seatTypes = seatTypeService.getAllSeatTypes();
        return new ResponseEntity<>(seatTypes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get seat type by ID", description = "Retrieve a seat type by its ID")
    public ResponseEntity<SeatTypeDTO> getSeatTypeById(@PathVariable("id") Long seatTypeId) {
        SeatTypeDTO seatType = seatTypeService.getSeatTypeById(seatTypeId);
        return new ResponseEntity<>(seatType, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update seat type", description = "Update an existing seat type with the provided details")
    public ResponseEntity<SeatTypeDTO> updateSeatType(@PathVariable("id") Long seatTypeId,
                                                      @RequestBody SeatTypeDTO seatTypeDTO) {
        seatTypeDTO.setSeatTypeId(seatTypeId);
        SeatTypeDTO updatedSeatType = seatTypeService.updateSeatType(seatTypeDTO);
        return new ResponseEntity<>(updatedSeatType, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete seat type", description = "Delete a seat type by its ID")
    public ResponseEntity<Void> deleteSeatType(@PathVariable("id") Long seatTypeId) {
        seatTypeService.deleteSeatType(seatTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}