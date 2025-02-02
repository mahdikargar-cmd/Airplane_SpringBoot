package com.Airplane.AirplaneApp.Mapper;

import com.Airplane.AirplaneApp.DTO.FlightDTO;
import com.Airplane.AirplaneApp.Entity.Aircraft;
import com.Airplane.AirplaneApp.Entity.Airport;
import com.Airplane.AirplaneApp.Entity.Flight;
import com.Airplane.AirplaneApp.Entity.FlightStatus;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDTO toDTO(Flight flight) {
        if (flight == null) return null;

        return new FlightDTO(
                flight.getFlightId(),
                flight.getFlightNumber(),
                flight.getAircraft() != null ? flight.getAircraft().getAircraftId() : null,
                flight.getDepartureAirport() != null ? flight.getDepartureAirport().getAirportId() : null,
                flight.getArrivalAirport() != null ? flight.getArrivalAirport().getAirportId() : null,
                flight.getScheduledDepartureTime(),
                flight.getScheduledArrivalTime(),
                flight.getStatus(),
                flight.getBasePrice(),
                flight.getGate(),
                flight.getRemarks()
        );
    }

    public Flight toEntity(FlightDTO dto) {
        if (dto == null) return null;

        Flight flight = new Flight();
        flight.setFlightId(dto.getFlightId());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setScheduledDepartureTime(dto.getScheduledDepartureTime());
        flight.setScheduledArrivalTime(dto.getScheduledArrivalTime());
        flight.setStatus(dto.getStatus() != null ? dto.getStatus() : FlightStatus.SCHEDULED);
        flight.setBasePrice(dto.getBasePrice());
        flight.setGate(dto.getGate());
        flight.setRemarks(dto.getRemarks());

        if (dto.getAircraftId() != null) {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(dto.getAircraftId());
            flight.setAircraft(aircraft);
        }

        if (dto.getDepartureAirportId() != null) { // بررسی مقدار null
            Airport departureAirport = new Airport();
            departureAirport.setAirportId(dto.getDepartureAirportId());
            flight.setDepartureAirport(departureAirport);
        } else {
            throw new IllegalArgumentException("Departure airport ID is required.");
        }

        if (dto.getArrivalAirportId() != null) { // بررسی مقدار null
            Airport arrivalAirport = new Airport();
            arrivalAirport.setAirportId(dto.getArrivalAirportId());
            flight.setArrivalAirport(arrivalAirport);
        } else {
            throw new IllegalArgumentException("Arrival airport ID is required.");
        }

        return flight;
    }

}
