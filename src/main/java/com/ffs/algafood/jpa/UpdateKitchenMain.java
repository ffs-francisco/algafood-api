package com.ffs.algafood.jpa;

import com.ffs.algafood.ApiApplication;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.repository.KitchenRepository;
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
