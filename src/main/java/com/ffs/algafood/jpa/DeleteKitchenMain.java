package com.ffs.algafood.jpa;

import com.ffs.algafood.ApiApplication;
import com.ffs.algafood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class DeleteKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRepository = appContext.getBean(KitchenRepository.class);

        deleteKitchen(kitchenRepository);
        System.out.println(kitchenRepository.findById(1L));
    }

    private static void deleteKitchen(KitchenRepository kitchenRepository) {
        kitchenRepository.deleteById(1L);
    }
}
