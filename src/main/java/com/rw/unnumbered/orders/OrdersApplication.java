package com.rw.unnumbered.orders;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
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

    @Bean
    public boolean configureGlobalTracer()	{
        Tracer tracer = Configuration.fromEnv().getTracer();
        GlobalTracer.register(tracer);
        return true;
    }

}
