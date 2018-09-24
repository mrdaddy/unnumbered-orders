package com.rw.unnumbered.orders.dto.passenger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ApiModel(description = "Данные о пассажире")
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @ApiModelProperty(example = "1", required = false, value = "Уникальный идентификатор пассажира из СППД. Если пассажир вводится первый раз - заполнять не нужно", dataType = "long")
    private long id;

    @ApiModelProperty(example = "Иван", required = true, value = "Имя пассажира (при покупке по глобальной цене допускается только латиница)", dataType = "String")
    @Size(min = 1, max = 32)
    private String firstName;

    @ApiModelProperty(example = "Иванович", required = false, value = "Отчество пассажира (при покупке по глобальной цене не заполняется)", dataType = "String")
    @Size(max = 32)
    private String patronymic;

    @ApiModelProperty(example = "Иванов", required = true, value = "Фамилия пассажира (при покупке по глобальной цене допускается только латиница)", dataType = "String")
    @Size(min = 1, max = 32)
    private String lastName;

    @ApiModelProperty(example = "ПБ", required = true, value = "Код типа документа из справочника “Справочник типов документов, удостоверяющих личность пассажира”", dataType = "String")
    @Size(min = 2, max = 2)
    private String documentType;

    @ApiModelProperty(example = "КН2014222", required = true, value = "Номер документа, удостоверяющего личность пассажира", dataType = "String")
    @Size(min = 1, max = 16)
    private String documentNumber;
}
