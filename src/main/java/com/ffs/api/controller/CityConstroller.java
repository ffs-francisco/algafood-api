package com.ffs.api.controller;

import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.City;
import com.ffs.api.domain.repository.CityRepository;
import com.ffs.api.domain.service.CityRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private CityRegistrationService cityRegistrationService;

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

    @PostMapping
    public ResponseEntity<?> add(@RequestBody final City city) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cityRegistrationService.save(city));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
