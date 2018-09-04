package com.rw.unnumbered.orders.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Service
@Validated
public class PDFService {
    public byte[] getTicketPDF(@Valid @Min(1) long orderId, long ticketId) {
        return new byte[200];
    }
}
