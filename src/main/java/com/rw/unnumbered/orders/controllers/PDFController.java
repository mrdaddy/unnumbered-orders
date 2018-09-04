package com.rw.unnumbered.orders.controllers;

import com.rw.unnumbered.orders.service.PDFService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="tickets/pdf", description="Сервис получения получения ЭПД в виде PDF-представления", tags = "Получение ЭПД в виде PDF-представления", basePath="/tickets/pdf")
@RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, path = "/${service.version}/unnumbered/orders")
public class PDFController extends BaseController {

    @Autowired
    PDFService pdfService;

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}/tickets/{ticketId}.pdf")
    @ApiOperation(value = "Получение ЭПД в виде PDF-представления")
    public @ResponseBody
    byte[] returnTicket(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор заказа", required = true) long orderId, @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор ЭПД", required = true) long ticketId) {
        return pdfService.getTicketPDF(orderId, ticketId);
    }
}
