package com.rw.unnumbered.orders.dao;

import com.rw.unnumbered.orders.dto.Order;
import com.rw.unnumbered.orders.dto.ShortOrder;
import com.rw.unnumbered.orders.dto.request.SearchOrderFilter;
import com.rw.unnumbered.orders.security.User;
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

    public List<ShortOrder> getOrders(SearchOrderFilter searchOrderFilter, User user) {
        List<ShortOrder> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }
}