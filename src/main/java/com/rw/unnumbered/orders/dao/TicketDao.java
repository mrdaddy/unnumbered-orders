package com.rw.unnumbered.orders.dao;

import com.rw.unnumbered.orders.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Transactional
@Repository
public class TicketDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Ticket activateTicket(long orderId, Date date, String train) {
        return new Ticket();
    }

}
