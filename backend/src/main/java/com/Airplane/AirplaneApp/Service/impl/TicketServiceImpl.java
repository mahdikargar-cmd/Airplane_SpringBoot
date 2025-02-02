package com.Airplane.AirplaneApp.Service.impl;

import com.Airplane.AirplaneApp.DTO.TicketDTO;
import com.Airplane.AirplaneApp.Entity.Ticket;
import com.Airplane.AirplaneApp.Entity.Airport;
import com.Airplane.AirplaneApp.Entity.Aircraft;
import com.Airplane.AirplaneApp.Mapper.TicketMapper;
import com.Airplane.AirplaneApp.Repository.TicketRepository;
import com.Airplane.AirplaneApp.Repository.AirportRepository;
import com.Airplane.AirplaneApp.Repository.AircraftRepository;
import com.Airplane.AirplaneApp.Service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final AirportRepository airportRepository;
    private final AircraftRepository aircraftRepository;

    @Override
    public TicketDTO bookTicket(TicketDTO ticketDTO) {
        // Validate airports and aircraft
        Airport departureAirport = airportRepository.findById(ticketDTO.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException("Departure airport not found"));
        Airport arrivalAirport = airportRepository.findById(ticketDTO.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException("Arrival airport not found"));
        Aircraft aircraft = aircraftRepository.findById(ticketDTO.getAircraftId())
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        // Generate unique ticket number
        String ticketNumber = generateTicketNumber();

        Ticket ticket = TicketMapper.mapToTicketEntity(ticketDTO);
        ticket.setTicketNumber(ticketNumber);
        ticket.setDepartureAirport(departureAirport);
        ticket.setArrivalAirport(arrivalAirport);
        ticket.setAircraft(aircraft);
        ticket.setPurchaseDateTime(LocalDateTime.now());
        ticket.setStatus("CONFIRMED");

        ticket = ticketRepository.save(ticket);
        return TicketMapper.mapToTicketDTO(ticket);
    }

    @Override
    public TicketDTO getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        return TicketMapper.mapToTicketDTO(ticket);
    }

    @Override
    public TicketDTO getTicketByNumber(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new RuntimeException("Ticket not found with number: " + ticketNumber));
        return TicketMapper.mapToTicketDTO(ticket);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(TicketMapper::mapToTicketDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> getTicketsByPassengerNationalId(String nationalId) {
        List<Ticket> tickets = ticketRepository.findByPassengerNationalId(nationalId);
        return tickets.stream()
                .map(TicketMapper::mapToTicketDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(ticketDTO.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketDTO.getTicketId()));

        updateTicketEntityFromDTO(ticket, ticketDTO);
        Ticket updatedTicket = ticketRepository.save(ticket);
        return TicketMapper.mapToTicketDTO(updatedTicket);
    }

    @Override
    public void cancelTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        ticket.setStatus("CANCELLED");
        ticketRepository.save(ticket);
    }

    @Override
    public List<TicketDTO> searchAvailableTickets(Long departureAirportId,
                                                  Long arrivalAirportId,
                                                  LocalDateTime departureTime) {
        List<Ticket> tickets = ticketRepository.findAvailableTickets(
                departureAirportId, arrivalAirportId, departureTime);
        return tickets.stream()
                .map(TicketMapper::mapToTicketDTO)
                .collect(Collectors.toList());
    }

    private String generateTicketNumber() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void updateTicketEntityFromDTO(Ticket ticket, TicketDTO ticketDTO) {
        if (ticketDTO.getPassengerName() != null) {
            ticket.setPassengerName(ticketDTO.getPassengerName());
        }
        if (ticketDTO.getPassengerNationalId() != null) {
            ticket.setPassengerNationalId(ticketDTO.getPassengerNationalId());
        }
        if (ticketDTO.getSeatNumber() != null) {
            ticket.setSeatNumber(ticketDTO.getSeatNumber());
        }
        if (ticketDTO.getTicketClass() != null) {
            ticket.setTicketClass(ticketDTO.getTicketClass());
        }
        if (ticketDTO.getPrice() != null) {
            ticket.setPrice(ticketDTO.getPrice());
        }
        if (ticketDTO.getStatus() != null) {
            ticket.setStatus(ticketDTO.getStatus());
        }
    }
}