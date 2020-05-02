package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class QueryKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRepository = appContext.getBean(KitchenRepository.class);

        insertKithen(kitchenRepository);
        kitchenRepository.findAll()
                .forEach((it) -> {
                    System.out.println(it.toString());
                });
    }

    private static void insertKithen(KitchenRepository kitchenRepository) {
        var kitchen01 = new Kitchen();
        kitchen01.setName("Brasileira");

        var kitchen02 = new Kitchen();
        kitchen02.setName("Japonesa");

        kitchenRepository.save(kitchen01);
        kitchenRepository.save(kitchen02);
    }
}
