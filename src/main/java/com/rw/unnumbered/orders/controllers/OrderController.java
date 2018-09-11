package com.rw.unnumbered.orders.controllers;

import com.rw.unnumbered.orders.dto.Order;
import com.rw.unnumbered.orders.dto.OrderingInformation;
import com.rw.unnumbered.orders.dto.Ticket;
import com.rw.unnumbered.orders.service.OrderService;
import com.rw.unnumbered.orders.validator.OrderingInformationValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value="orders", description="Сервис базовых операций с ненумерованными  заказами пользователя", tags = "Основные операции с заказами пользователя", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/unnumbered/orders")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new OrderingInformationValidator());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Создание нового заказа", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.CREATED)
    public Order createOrder(@RequestBody @ApiParam OrderingInformation orderingInformation) {
        return orderService.createOrder(orderingInformation);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/activate")
    @ApiOperation(value = "Активировать ЭПД", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket activateTicket(@RequestParam(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор заказа", required = true) long orderId,
                                 @RequestParam @ApiParam(example = "2018-01-20", value = "Дата поездки", required = true)  @DateTimeFormat(pattern="yyyy-dd-MM") Date date,
                                 @RequestParam @ApiParam(example = "001А") String train) {
        return orderService.activateTicket(orderId, date, train);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    @ApiOperation(value = "Удаление неоплаченного заказа из корзины с аннулированием в системе ЭПД", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.ACCEPTED)
    public void deleteOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор заказа", example = "1") long orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    @ApiOperation(value = "Получение информации о заказе пользователя", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    responseHeaders = {
                            @ResponseHeader(name = "ETag", response = String.class, description = "Хеш для кэширования")}),
            @ApiResponse(code = 304, message = "Not Modified")
    })
    public Order getOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор  заказа", example = "1") long orderId, @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm) {
        return orderService.getOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Получение информации о заказах пользователя", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    responseHeaders = {
                            @ResponseHeader(name = "ETag", response = String.class, description = "Хеш для кэширования")}),
            @ApiResponse(code = 304, message = "Not Modified")
    })
    public List<Order> getOrders(@RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов пользователя по типу заказа. Значение: пусто - все заказы, upcoming - заказы с предстоящими поездками, past - заказы с прошедшими поездками", example = "past", defaultValue = "upcoming", allowableValues = "upcoming, past") String orderType,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с датой отправления больше либо равно указанной", example = "2018-11-12")  @DateTimeFormat(pattern="yyyy-dd-MM") Date departureDateMin,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с датой отправления меньше либо равно указанной", example = "2018-11-22")  @DateTimeFormat(pattern="yyyy-dd-MM") Date departureDateMax,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с указанным поездом", example = "001А")String train,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с указанной станцией отправления", example = "2100276") String departureStationCode,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с указанной станцией прибытия", example = "2100276") String arrivalStationCode,
                                 @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm) {
        return orderService.getOrders(orderType ,departureDateMin, departureDateMax, train, departureStationCode, arrivalStationCode);
    }

}


