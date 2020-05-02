package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class DeleteKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRegistration = appContext.getBean(KitchenRegistration.class);

        deleteKitchen(kitchenRegistration);
        System.out.println(kitchenRegistration.getById(1L));
    }

    private static void deleteKitchen(KitchenRegistration kitchenRegistration) {
        var kitcken = new Kitchen();
        kitcken.setId(1L);

        kitchenRegistration.delete(kitcken);
    }

}
