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
public class UpdateKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRepository = appContext.getBean(KitchenRepository.class);

        insertKitchen(kitchenRepository);
        System.out.println(kitchenRepository.findById(1L));
    }

    private static void insertKitchen(KitchenRepository kitchenRepository) {
        var kitcken = new Kitchen();
        kitcken.setId(1L);
        kitcken.setName("Nordestina");

        kitchenRepository.save(kitcken);
    }

}
