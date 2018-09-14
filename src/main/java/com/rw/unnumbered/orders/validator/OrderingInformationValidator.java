package com.rw.unnumbered.orders.validator;

import com.rw.unnumbered.orders.dto.request.OrderingInformation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderingInformationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderingInformation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        OrderingInformation orderingInformation = (OrderingInformation) target;
       /* if(orderingInformation.getArrStationCode().equals("string")) {
            e.reject("gvCode","test validation");
        }*/
    }
}
