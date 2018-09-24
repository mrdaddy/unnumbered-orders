package com.rw.unnumbered.orders.controllers;

import com.rw.unnumbered.orders.dto.Order;
import com.rw.unnumbered.orders.dto.request.OrderingInformation;
import com.rw.unnumbered.orders.dto.Ticket;
import com.rw.unnumbered.orders.dto.request.SearchOrderFilter;
import com.rw.unnumbered.orders.security.User;
import com.rw.unnumbered.orders.service.OrderService;
import com.rw.unnumbered.orders.validator.OrderingInformationValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @RequestMapping(method = RequestMethod.POST, path = "/auth")
    @ApiOperation(value = "Создание нового заказа авторизованным пользователем", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.CREATED)
    @PreAuthorize("hasRole('U')")
    public Order createOrderAuth(@RequestBody @ApiParam(required = true) OrderingInformation orderingInformation) {
        return orderService.createOrderAuth(orderingInformation);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/notauth")
    @ApiOperation(value = "Создание нового заказа неавторизованным пользователем")
    @ResponseStatus( HttpStatus.CREATED)
    public Order createOrderNotAuth(@RequestBody @ApiParam(required = true) OrderingInformation orderingInformation, @RequestParam @ApiParam(required = true, example = "test@test.com", value = "Email пользователя") String email, @RequestParam @ApiParam(example = "+375295544333", value = "Телефон пользователя") String phone) {
        return orderService.createOrderNotAuth(orderingInformation, email, phone);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/activate")
    @ApiOperation(value = "Активировать ЭПД", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('U')")
    public Ticket activateTicket(@RequestParam(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор заказа", required = true) long orderId,
                                 @RequestParam @ApiParam(example = "2018-01-20", value = "Дата поездки", required = true)  @DateTimeFormat(pattern="yyyy-dd-MM") Date date,
                                 @RequestParam @ApiParam(example = "001А") String train) {
        return orderService.activateTicket(orderId, date, train);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    @ApiOperation(value = "Удаление неоплаченного заказа из корзины с аннулированием в системе ЭПД", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('U')")
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
    @PreAuthorize("hasRole('U')")
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
    @PreAuthorize("hasRole('U')")
    public List<Order> getOrders(@RequestParam(required = false) SearchOrderFilter searchOrderFilter,
                                 @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm,
                                 @RequestAttribute(value = "user", required = false) @ApiIgnore User user)   {
        return orderService.getOrders(searchOrderFilter, user);
    }

}


