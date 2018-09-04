package com.rw.unnumbered.orders.dao;

import com.rw.unnumbered.orders.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Order> getOrders(String filter) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }
}