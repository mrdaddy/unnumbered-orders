package com.rw.unnumbered.orders.service;

import com.rw.unnumbered.orders.dao.OrderDao;
import com.rw.unnumbered.orders.dao.TicketDao;
import com.rw.unnumbered.orders.dto.Order;
import com.rw.unnumbered.orders.dto.request.OrderingInformation;
import com.rw.unnumbered.orders.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    TicketDao ticketDao;

    public Order createOrderAuth(@Valid OrderingInformation orderingInformation) {
        return new Order();
    }

    public Order createOrderNotAuth(@Valid OrderingInformation orderingInformation, @Valid @NotNull @Email @Size(max=64) String email, @Valid @Size(max=255) String phone) {
        return new Order();
    }

    public Ticket activateTicket(@Valid @Min(1) long orderId, @Valid Date date, @Valid @Size(min=4,max=5) String train) {
        return ticketDao.activateTicket(orderId, date, train);
    }

    public void deleteOrder(@Valid @Min(1) long orderId) {

    }

    public Order getOrder(@Valid @Min(1) long orderId) {
        return new Order();
    }

    public List<Order> getOrders(@Valid @Size(max = 30) String orderType , @Valid Date departureDateMin, @Valid Date departureDateMax, @Valid @Size( max=6) String train,
                                 @Valid @Size( max=8) String departureStationCode, @Valid @Size( max=8) String  arrivalStationCode) {
        return orderDao.getOrders(orderType ,departureDateMin, departureDateMax, train, departureStationCode, arrivalStationCode);
    }

}
