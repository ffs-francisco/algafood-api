package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class SearchKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRegistration = appContext.getBean(KitchenRegistration.class);

        System.out.println(kitchenRegistration.getById(1L));
    }

}
