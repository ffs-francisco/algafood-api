package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.domain.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static java.lang.String.format;

/**
 *
 * @author francisco
 */
@Slf4j
public class QueryRestaurantMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var restaurantRepository = appContext.getBean(RestaurantRepository.class);

        restaurantRepository.findAll()
                .forEach((it) -> {
                    log.info(format("Restaurante %s com frete %f - cozinha %s ", it.getName(), it.getShippingFee(), it.getKitchen().getName()));
                });
    }
}
