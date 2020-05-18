package com.ffs.algafood.jpa;

import com.ffs.algafood.ApiApplication;
import com.ffs.algafood.domain.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static java.lang.String.format;

/**
 *
 * @author francisco
 */
@Slf4j
public class SearchCityMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var cityRepository = appContext.getBean(CityRepository.class);

        log.info("Todas as cidades");
        cityRepository.findAll().forEach(city -> {
            log.info(format("%s - %s", city.getName(), city.getState().getName()));
        });
    }
}
