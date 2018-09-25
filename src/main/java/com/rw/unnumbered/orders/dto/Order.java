package com.rw.unnumbered.orders.dto;

import com.rw.unnumbered.orders.dto.passenger.Passenger;
import com.rw.unnumbered.orders.dto.request.OrderingInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Полная информация о заказе пользователя")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends ShortOrder{

    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор маршрута)", dataType = "String")
    private String routeId;

    @ApiModelProperty(example = "REG_LINE", required = true, value = "Тип поезда. Значения: REG_LINE - региональные линии эконом-класса, CITY_LINE - городские линии", dataType = "String")
    private OrderingInformation.TRAIN_TYPE trainType;

    @ApiModelProperty(example = "1", required = false, value = "Код багажа", dataType = "String")
    private String baggageType;

    @ApiModelProperty(example = "1", required = false, value = "Код льготы", dataType = "String")
    private String privilegeType;

    @ApiModelProperty(required = true, value = "Данные о пассажире")
    private Passenger passenger;

    private List<Ticket> tickets;

    @ApiModelProperty(required = false, value = "JWT токен для авторизации. Заполняется в случае вызова функции покупки билета без авторизации по email")
    private String jwtToken;

}
