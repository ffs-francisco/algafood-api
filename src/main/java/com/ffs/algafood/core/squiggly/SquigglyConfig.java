package com.ffs.algafood.core.squiggly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author francisco
 */
@Configuration
public class SquigglyConfig {

    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(final ObjectMapper mapper) {
        Squiggly.init(mapper, new RequestSquigglyContextProvider());

        final var filterResgistration = new FilterRegistrationBean<SquigglyRequestFilter>();
        filterResgistration.setFilter(new SquigglyRequestFilter());
        filterResgistration.setOrder(1);

        return filterResgistration;
    }
}
