package com.rw.unnumbered.orders.dto;

import com.rw.unnumbered.orders.dto.request.OrderingInformation;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ShortOrder {
    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор заказа)", dataType = "long")
    private long orderId;

    @ApiModelProperty(example = "TO", required = false, value = "Направление. Значения: TO - туда, BACK - обратно. Поддерживается только TO", dataType = "String")
    private OrderingInformation.DIRECTION directionType;

    @ApiModelProperty(example = "ORDER_TYPE", required = true, value = "Тип заказа. Значения: DATE - на дату, TRAIN - на поезд, TRIPS - на количество поездок", dataType = "String")
    private OrderingInformation.ORDER_TYPE ORDER_TYPE;

    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор корзины", dataType = "String")
    private String basketId;

    @ApiModelProperty(example = "001А", required = true, value = "Номер поезда", dataType = "String")
    private String train;

    @ApiModelProperty(example = "2018-08-24", required = false, value = "Дата отправления со станции пассажира", dataType = "Date")
    private Date departureDate;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции отправления", dataType = "String")
    private String depStationCode;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции назначения", dataType = "String")
    private String arrStationCode;

    @ApiModelProperty(example = "12.12", required = true, value = "Сумма заказа", dataType = "double")
    private double cost;

    @ApiModelProperty(example = "12.12", required = true, value = "Стоимость ЭПД", dataType = "double")
    private double ticketPrice;

    @ApiModelProperty(example = "2", required = true, value = "Количество поездок", dataType = "int")
    private int tripCount;

    @ApiModelProperty(example = "2", required = true, value = "Количество активированных поездок (количество ЭПД)", dataType = "int")
    private int activatedTripCount;

    @ApiModelProperty(example = "FULL", required = true, value = "Тип ЭПД. Значения: FULL - полный, PRIVILEGE - льготный, FREE - детский бесплатный, BAGGAGE - багаж", dataType = "String")
    private OrderingInformation.TICKET_TYPE ticketType;
}
