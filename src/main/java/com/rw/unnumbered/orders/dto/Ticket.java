package com.rw.unnumbered.orders.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(description = "Информация об ЭПД")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @ApiModelProperty(example = "12345", required = true, value = "Идентификатор заказа", dataType = "long")
    private long ticketId;

    @ApiModelProperty(required = true, value = "Дата и время активации заказа", dataType = "Datetime")
    private Date activationTime;

    @ApiModelProperty(required = true, example = "2018-01-01", value = "Дата поездки", dataType = "Date")
    private Date departureDate;

    @ApiModelProperty(required = false, example = "001А", value = "Номер поезда", dataType = "String")
    private String train;
}
