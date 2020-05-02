package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.KitchenRegistration;
import com.ffs.api.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class QueryKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRegistration = appContext.getBean(KitchenRegistration.class);

        insertKithen(kitchenRegistration);
        kitchenRegistration.list()
                .forEach((it) -> {
                    System.out.println(it.toString());
                });
    }

    private static void insertKithen(KitchenRegistration kitchenRegistration) {
        var kitchen01 = new Kitchen();
        kitchen01.setName("Brasileira");

        var kitchen02 = new Kitchen();
        kitchen02.setName("Japonesa");

        kitchenRegistration.add(kitchen01);
        kitchenRegistration.add(kitchen02);
    }
}
