package com.Airplane.AirplaneApp.Controller;

import com.Airplane.AirplaneApp.DTO.TicketDTO;
import com.Airplane.AirplaneApp.Service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/ticket")
@AllArgsConstructor
@Tag(name = "Ticket Management", description = "APIs for managing flight tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("book")
    @Operation(summary = "Book a new ticket", description = "Create a new ticket booking with the provided details")
    public ResponseEntity<TicketDTO> bookTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO bookedTicket = ticketService.bookTicket(ticketDTO);
        return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
    }

    @GetMapping("listAll")
    @Operation(summary = "Get all tickets", description = "Retrieve a list of all tickets")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get ticket by ID", description = "Retrieve a ticket by its ID")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable("id") Long ticketId) {
        TicketDTO ticket = ticketService.getTicketById(ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("number/{ticketNumber}")
    @Operation(summary = "Get ticket by number", description = "Retrieve a ticket by its number")
    public ResponseEntity<TicketDTO> getTicketByNumber(@PathVariable String ticketNumber) {
        TicketDTO ticket = ticketService.getTicketByNumber(ticketNumber);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("passenger/{nationalId}")
    @Operation(summary = "Get tickets by passenger ID", description = "Retrieve all tickets for a specific passenger")
    public ResponseEntity<List<TicketDTO>> getTicketsByPassenger(@PathVariable String nationalId) {
        List<TicketDTO> tickets = ticketService.getTicketsByPassengerNationalId(nationalId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("search")
    @Operation(summary = "Search available tickets", description = "Search for available tickets based on criteria")
    public ResponseEntity<List<TicketDTO>> searchTickets(
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime) {
        List<TicketDTO> availableTickets = ticketService.searchAvailableTickets(
                departureAirportId, arrivalAirportId, departureTime);
        return new ResponseEntity<>(availableTickets, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update a ticket", description = "Update an existing ticket with the provided details")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Long ticketId,
                                                  @RequestBody TicketDTO ticketDTO) {
        ticketDTO.setTicketId(ticketId);
        TicketDTO updatedTicket = ticketService.updateTicket(ticketDTO);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PutMapping("cancel/{id}")
    @Operation(summary = "Cancel a ticket", description = "Cancel an existing ticket booking")
    public ResponseEntity<Void> cancelTicket(@PathVariable("id") Long ticketId) {
        ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}