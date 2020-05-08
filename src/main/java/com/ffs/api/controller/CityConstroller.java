package com.ffs.api.controller;

import com.ffs.api.domain.model.City;
import com.ffs.api.domain.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/citys")
public class CityConstroller {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> listAll() {
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> gitById(@PathVariable final Long cityId) {
        final var city = cityRepository.findById(cityId);
        if (city == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(city);
    }
}
