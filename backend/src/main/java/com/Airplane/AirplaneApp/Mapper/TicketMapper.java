package com.Airplane.AirplaneApp.Mapper;

import com.Airplane.AirplaneApp.DTO.TicketDTO;
import com.Airplane.AirplaneApp.Entity.Ticket;

public class TicketMapper {
    public static TicketDTO mapToTicketDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(ticket.getTicketId());
        ticketDTO.setTicketNumber(ticket.getTicketNumber());
        ticketDTO.setFlightId(ticket.getFlightId());
        ticketDTO.setPassengerName(ticket.getPassengerName());
        ticketDTO.setPassengerNationalId(ticket.getPassengerNationalId());
        ticketDTO.setSeatNumber(ticket.getSeatNumber());
        ticketDTO.setTicketClass(ticket.getTicketClass());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setPurchaseDateTime(ticket.getPurchaseDateTime());
        ticketDTO.setStatus(ticket.getStatus());
        ticketDTO.setDepartureAirportId(ticket.getDepartureAirport().getAirportId());
        ticketDTO.setArrivalAirportId(ticket.getArrivalAirport().getAirportId());
        ticketDTO.setAircraftId(ticket.getAircraft().getAircraftId());
        ticketDTO.setDepartureTime(ticket.getDepartureTime());
        ticketDTO.setArrivalTime(ticket.getArrivalTime());
        return ticketDTO;
    }

    public static Ticket mapToTicketEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDTO.getTicketId());
        ticket.setTicketNumber(ticketDTO.getTicketNumber());
        ticket.setFlightId(ticketDTO.getFlightId());
        ticket.setPassengerName(ticketDTO.getPassengerName());
        ticket.setPassengerNationalId(ticketDTO.getPassengerNationalId());
        ticket.setSeatNumber(ticketDTO.getSeatNumber());
        ticket.setTicketClass(ticketDTO.getTicketClass());
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setPurchaseDateTime(ticketDTO.getPurchaseDateTime());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setDepartureTime(ticketDTO.getDepartureTime());
        ticket.setArrivalTime(ticketDTO.getArrivalTime());
        return ticket;
    }
}