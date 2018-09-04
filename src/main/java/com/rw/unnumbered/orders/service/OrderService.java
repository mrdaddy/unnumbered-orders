package com.rw.unnumbered.orders.service;

import com.rw.unnumbered.orders.dao.OrderDao;
import com.rw.unnumbered.orders.dto.Order;
import com.rw.unnumbered.orders.dto.OrderingInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public Order createOrder(@Valid OrderingInformation orderingInformation) {
        return new Order();
    }

    public long activateTicket(@Valid @Min(1) long orderId, @Valid Date date, @Valid @Size(min=4,max=5) String train) {
        return 1;
    }

    public void deleteOrder(@Valid @Min(1) long orderId) {

    }

    public Order getOrder(@Valid @Min(1) long orderId) {
        return new Order();
    }

    public List<Order> getOrders(@Valid @Size(max = 30) String filter) {
        return orderDao.getOrders(filter);
    }

}
