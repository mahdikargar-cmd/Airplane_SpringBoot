package com.Airplane.AirplaneApp.Service.impl;

import com.Airplane.AirplaneApp.DTO.PassengerDTO;
import com.Airplane.AirplaneApp.Entity.Passenger;
import com.Airplane.AirplaneApp.Mapper.PassengerMapper;
import com.Airplane.AirplaneApp.Repository.PassengerRepository;
import com.Airplane.AirplaneApp.Service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public PassengerDTO addPassenger(PassengerDTO passengerDTO) {
        if (passengerRepository.existsByNationalCode(passengerDTO.getNationalCode())) {
            throw new RuntimeException("Passenger with this national code already exists");
        }
        if (passengerDTO.getPassportNumber() != null &&
                passengerRepository.existsByPassportNumber(passengerDTO.getPassportNumber())) {
            throw new RuntimeException("Passenger with this passport number already exists");
        }

        Passenger passenger = PassengerMapper.mapToPassengerEntity(passengerDTO);
        passenger = passengerRepository.save(passenger);
        return PassengerMapper.mapToPassengerDTO(passenger);
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(PassengerMapper::mapToPassengerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PassengerDTO getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
        return PassengerMapper.mapToPassengerDTO(passenger);
    }

    @Override
    public PassengerDTO updatePassenger(PassengerDTO passengerDTO) {
        Passenger existingPassenger = passengerRepository.findById(passengerDTO.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + passengerDTO.getPassengerId()));

        // Check if updated national code conflicts with another passenger
        if (!existingPassenger.getNationalCode().equals(passengerDTO.getNationalCode()) &&
                passengerRepository.existsByNationalCode(passengerDTO.getNationalCode())) {
            throw new RuntimeException("Passenger with this national code already exists");
        }

        // Check if updated passport number conflicts with another passenger
        if (passengerDTO.getPassportNumber() != null &&
                !passengerDTO.getPassportNumber().equals(existingPassenger.getPassportNumber()) &&
                passengerRepository.existsByPassportNumber(passengerDTO.getPassportNumber())) {
            throw new RuntimeException("Passenger with this passport number already exists");
        }

        Passenger passenger = PassengerMapper.mapToPassengerEntity(passengerDTO);
        passenger = passengerRepository.save(passenger);
        return PassengerMapper.mapToPassengerDTO(passenger);
    }

    @Override
    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        passengerRepository.deleteById(id);
    }
}