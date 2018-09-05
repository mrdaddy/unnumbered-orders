package com.rw.unnumbered.orders.dao;

import com.rw.unnumbered.orders.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Order> getOrders(String orderType , Date departureDateMin, Date departureDateMax, String train,
                                 String departureStationCode, String  arrivalStationCode) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }
}