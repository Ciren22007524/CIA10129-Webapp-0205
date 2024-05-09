package com.ren.config;

import com.ren.filter.backend.AutoLoginFilter;
import com.ren.filter.backend.LoginStateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

import static com.ren.util.Constants.FIRST_ORDER;
import static com.ren.util.Constants.SECOND_ORDER;

@Configuration
public class FilterConfig {

    @Autowired
    private AutoLoginFilter autoLoginFilter;

    @Autowired
    private LoginStateFilter loginStateFilter;

    @Bean
    public FilterRegistrationBean<Filter> registration1() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(autoLoginFilter);
        registration.addUrlPatterns("/backend/*");
        registration.setOrder(FIRST_ORDER);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<Filter> registration2() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(loginStateFilter);
        registration.addUrlPatterns("/backend/*");
        registration.setOrder(SECOND_ORDER);
        return registration;
    }

}
