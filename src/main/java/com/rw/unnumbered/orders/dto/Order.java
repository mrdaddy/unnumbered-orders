package com.rw.unnumbered.orders.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Информация о заказе пользователя")
public class Order {

    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор заказа)", dataType = "long")
    private long orderId;

    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор корзины", dataType = "String")
    private String basketId;

    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор маршрута)", dataType = "String")
    private String routeId;

    @ApiModelProperty(example = "001А", required = true, value = "Номер поезда", dataType = "String")
    private String train;

    @ApiModelProperty(example = "2018-08-24", required = false, value = "Дата отправления со станции пассажира", dataType = "Date")
    private Date departureDate;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции отправления", dataType = "String")
    private String depStationCode;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции назначения", dataType = "String")
    private String arrStationCode;

    @ApiModelProperty(example = "THERE", required = false, value = "Направление. Значения: THERE - туда, BACK - обратно. Поддерживается только THERE, можно не заполнять", dataType = "String")
    private OrderingInformation.DIRECTION directionType;

    @ApiModelProperty(example = "REG_LINE", required = true, value = "Тип поезда. Значения: REG_LINE - региональные линии эконом-класса, CITY_LINE - городские линии", dataType = "String")
    private OrderingInformation.TRAIN_TYPE trainType;

    @ApiModelProperty(example = "FULL", required = true, value = "Тип ЭПД. Значения: FULL - полный, PRIVILEGE - льготный, FREE - детский бесплатный, BAGGAGE - багаж", dataType = "String")
    private OrderingInformation.TICKET_TYPE ticketType;

    @ApiModelProperty(example = "1", required = false, value = "Код багажа", dataType = "String")
    private String baggageType;

    @ApiModelProperty(example = "1", required = false, value = "Код льготы", dataType = "String")
    private String privilegeType;

    @ApiModelProperty(example = "12.12", required = true, value = "Сумма заказа", dataType = "double")
    private double cost;

    @ApiModelProperty(example = "12.12", required = true, value = "Стоимость ЭПД", dataType = "double")
    private double ticketPrice;

    @ApiModelProperty(example = "2", required = true, value = "Количество поездок", dataType = "int")
    private int tripCount;

    @ApiModelProperty(example = "2", required = true, value = "Количество активированных поездок (количество ЭПД)", dataType = "int")
    private int activatedTripCount;

    @ApiModelProperty(required = true, value = "Данные о пассажире")
    private Passenger passenger;

    private List<Ticket> tickets;
}
