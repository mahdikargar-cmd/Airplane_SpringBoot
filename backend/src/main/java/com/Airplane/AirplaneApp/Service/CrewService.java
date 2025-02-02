package com.Airplane.AirplaneApp.Service;

import com.Airplane.AirplaneApp.Entity.*;
import com.Airplane.AirplaneApp.Repository.*;
import com.Airplane.AirplaneApp.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CrewService {
    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private CrewTypeRepository crewTypeRepository;

    @Autowired
    private FlightCrewRepository flightCrewRepository;

    @Transactional
    public CrewMember createCrewMember(CrewMember crewMember) {
        validateCrewMember(crewMember);
        return crewRepository.save(crewMember);
    }

    public CrewMember getCrewMember(Long id) {
        return crewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crew member not found with id: " + id));
    }

    @Transactional
    public FlightCrew assignCrewToFlight(Long flightId, Set<CrewMember> crewMembers) {
        validateCrewAssignment(crewMembers);

        FlightCrew flightCrew = new FlightCrew();
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        flightCrew.setFlight(flight);
        flightCrew.setCrewMembers(crewMembers);
        flightCrew.setStatus("ASSIGNED");

        return flightCrewRepository.save(flightCrew);
    }

    public List<CrewMember> getAvailableCrew(String crewTypeCode) {
        return crewRepository.findByCrewType_CodeAndStatus(crewTypeCode, "ACTIVE");
    }

    private void validateCrewMember(CrewMember crewMember) {
        // Validate crew type
        crewTypeRepository.findById(crewMember.getCrewType().getCrewTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Crew type not found"));

        // Add additional validation logic here
    }

    private void validateCrewAssignment(Set<CrewMember> crewMembers) {
        // Validate minimum crew requirements
        long flightDeckCrewCount = crewMembers.stream()
                .filter(cm -> cm.getCrewType().getCategory().equals("FLIGHT_DECK_CREW"))
                .count();

        long cabinCrewCount = crewMembers.stream()
                .filter(cm -> cm.getCrewType().getCategory().equals("CABIN_CREW"))
                .count();

        if (flightDeckCrewCount < 2) {
            throw new IllegalArgumentException("Minimum two flight deck crew members required");
        }

        if (cabinCrewCount < 3) {
            throw new IllegalArgumentException("Minimum three cabin crew members required");
        }
    }
}