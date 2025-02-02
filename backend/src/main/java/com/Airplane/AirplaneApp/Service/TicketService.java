package com.Airplane.AirplaneApp.Service;

import com.Airplane.AirplaneApp.DTO.TicketDTO;
import java.util.List;
import java.time.LocalDateTime;

public interface TicketService {
    TicketDTO bookTicket(TicketDTO ticketDTO);
    TicketDTO getTicketById(Long id);
    TicketDTO getTicketByNumber(String ticketNumber);
    List<TicketDTO> getAllTickets();
    List<TicketDTO> getTicketsByPassengerNationalId(String nationalId);
    TicketDTO updateTicket(TicketDTO ticketDTO);
    void cancelTicket(Long id);
    List<TicketDTO> searchAvailableTickets(Long departureAirportId,
                                           Long arrivalAirportId,
                                           LocalDateTime departureTime);
}