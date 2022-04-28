package com.codegym.service;

import com.codegym.model.Ticket;

import java.util.List;

public interface ITicketService {


    List<Ticket>getListTypeSeatAndFlightId(Long idFlight,String typeSeat);
}
