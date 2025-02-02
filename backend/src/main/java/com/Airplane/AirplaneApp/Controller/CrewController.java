package com.Airplane.AirplaneApp.Controller;

import com.Airplane.AirplaneApp.Entity.CrewMember;
import com.Airplane.AirplaneApp.Entity.FlightCrew;
import com.Airplane.AirplaneApp.Service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/crew")
public class CrewController {
    @Autowired
    private CrewService crewService;

    @PostMapping
    public ResponseEntity<CrewMember> createCrewMember(@RequestBody CrewMember crewMember) {
        return ResponseEntity.ok(crewService.createCrewMember(crewMember));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewMember> getCrewMember(@PathVariable Long id) {
        return ResponseEntity.ok(crewService.getCrewMember(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<CrewMember>> getAvailableCrew(
            @RequestParam String crewTypeCode) {
        return ResponseEntity.ok(crewService.getAvailableCrew(crewTypeCode));
    }

    @PostMapping("/assign/{flightId}")
    public ResponseEntity<FlightCrew> assignCrewToFlight(
            @PathVariable Long flightId,
            @RequestBody Set<CrewMember> crewMembers) {
        return ResponseEntity.ok(crewService.assignCrewToFlight(flightId, crewMembers));
    }
}