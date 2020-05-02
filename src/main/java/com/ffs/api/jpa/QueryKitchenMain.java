package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.KitchenRegistration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class QueryKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        appContext.getBean(KitchenRegistration.class)
                .list().forEach((it) -> {
                    System.out.println(it.toString());
                });

    }
}
