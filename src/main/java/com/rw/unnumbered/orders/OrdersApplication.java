package com.rw.unnumbered.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@SpringBootApplication
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean shallowEtagBean() {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new ShallowEtagHeaderFilter());
        frb.addUrlPatterns("/");
        frb.setOrder(2);
        return frb;
    }

}
