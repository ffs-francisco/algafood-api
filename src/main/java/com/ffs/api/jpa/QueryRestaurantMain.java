package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.domain.model.Restaurant;
import com.ffs.api.domain.repository.RestaurantRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class QueryRestaurantMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var restaurantRepository = appContext.getBean(RestaurantRepository.class);

        insertKithen(restaurantRepository);
        restaurantRepository.findAll()
                .forEach((it) -> {
                    System.out.println(it.toString());
                });
    }

    private static void insertKithen(RestaurantRepository restaurantRepository) {
        var restaurant01 = new Restaurant();
        restaurant01.setName("Brasileiro");
        restaurant01.setShippingFee(new BigDecimal(BigInteger.TEN));

        var restaurant02 =  new Restaurant();
        restaurant02.setName("Japones");
        restaurant02.setShippingFee(new BigDecimal(BigInteger.ONE));
        

        restaurantRepository.save(restaurant01);
        restaurantRepository.save(restaurant02);
    }
}
