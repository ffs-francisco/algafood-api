package com.ffs.api.jpa;

import com.ffs.api.ApiApplication;
import com.ffs.api.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author francisco
 */
public class SearchKitchenMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var kitchenRepository = appContext.getBean(KitchenRepository.class);

        System.out.println(kitchenRepository.findById(1L));
    }

}
