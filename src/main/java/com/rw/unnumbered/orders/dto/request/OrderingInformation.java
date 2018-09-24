package com.rw.unnumbered.orders.dto.request;

import com.rw.unnumbered.orders.dto.passenger.Passenger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder
@ApiModel(description = "Данные для создания заказа о поездке и пассажирах")
@NoArgsConstructor
@AllArgsConstructor
public class OrderingInformation {
    public enum DIRECTION {TO, BACK}
    public enum TRAIN_TYPE {REG_LINE, CITY_LINE}
    public enum TICKET_TYPE {FULL, PRIVILEGE, FREE, BAGGAGE}
    public enum ORDER_TYPE {DATE, TRAIN, TRIPS}

    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор корзины, куда добавить заказ (необязательный, система сможет автоматически вычислить действующую активную корзину, если она есть)", dataType = "String")
    @Size(max=20)
    private String basketId;

    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор маршрута)", dataType = "String")
    @Size(min=1, max=20)
    private String routeId;

    @ApiModelProperty(example = "001А", required = true, value = "Номер поезда", dataType = "String")
    @Size(min=4,max=5)
    private String train;

    @ApiModelProperty(example = "2018-08-24", required = false, value = "Дата отправления со станции пассажира", dataType = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции отправления", dataType = "String")
    @NotNull @Size(min=7,max=8)
    private String depStationCode;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции назначения", dataType = "String")
    @NotNull @Size(min=7,max=8)
    private String arrStationCode;

    @ApiModelProperty(example = "THERE", required = false, value = "Направление. Значения: THERE - туда, BACK - обратно. Поддерживается только THERE, можно не заполнять", dataType = "String")
    @Builder.Default
    private DIRECTION directionType = DIRECTION.TO;

    @ApiModelProperty(example = "REG_LINE", required = true, value = "Тип поезда. Значения: REG_LINE - региональные линии эконом-класса, CITY_LINE - городские линии", dataType = "String")
    @NotNull
    private TRAIN_TYPE trainType;

    @ApiModelProperty(example = "ORDER_TYPE", required = true, value = "Тип заказа. Значения: DATE - на дату (дата должна быть больше текущей, поезд не учитывается), TRAIN - на поезд (дата должна быть текущая, номер поезда - обязателен), TRIPS - на количество поездок (дата и номер поезда не учитываются)", dataType = "String")
    @NotNull
    private ORDER_TYPE ORDER_TYPE;

    @ApiModelProperty(example = "FULL", required = true, value = "Тип ЭПД. Значения: FULL - полный, PRIVILEGE - льготный, FREE - детский бесплатный, BAGGAGE - багаж", dataType = "String")
    @NotNull
    private TICKET_TYPE ticketType;

    @ApiModelProperty(example = "1", required = false, value = "Код багажа", dataType = "String")
    @Size(max=32)
    private String baggageType;

    @ApiModelProperty(example = "1", required = false, value = "Код льготы", dataType = "String")
    @Size(max=32)
    private String privilegeType;

    @ApiModelProperty(example = "12.12", required = true, value = "Сумма заказа", dataType = "double")
    @DecimalMax("10000.0") @DecimalMin("0.0")
    private double cost;

    @ApiModelProperty(example = "12.12", required = true, value = "Стоимость ЭПД", dataType = "double")
    @DecimalMax("10000.0") @DecimalMin("0.0")
    private double ticketPrice;

    @ApiModelProperty(example = "2", required = false, value = "Количество поездок", dataType = "int")
    @Max(100)
    @Builder.Default
    private int tripCount = 1;

    @ApiModelProperty(required = true, value = "Данные о пассажире")
    private Passenger passenger;

    @ApiModelProperty(example = "100", required = true, value = "Дальность маршрута в километрах (заполняется значением поля distance сущности Route)", dataType = "int")
    @Max(10000)
    private int distance;

    @ApiModelProperty(example = "100", required = true, value = "Полная цена билета (заполняется значением поля price сущности Route)", dataType = "double")
    @DecimalMax("10000.0") @DecimalMin("0.0")
    private double fullPrice;

    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор базового тарифа для маршрута (заполняется значением поля tariffId сущности Route)", dataType = "String")
    @Size(max=20)
    private String tariffId;

}
