package com.ffs.algafood.api.controller;

import com.ffs.algafood.domain.exception.StateNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.model.City;
import com.ffs.algafood.domain.service.CityService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/citys")
public class CityConstroller {

    @Autowired
    private CityService cityService;

    @GetMapping
    @ResponseStatus(OK)
    public List<City> listAll() {
        return cityService.findAll();
    }

    @GetMapping("/{cityId}")
    @ResponseStatus(OK)
    public City gitById(@PathVariable final Long cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public City add(@RequestBody final City city) {
        try {
            return cityService.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    @ResponseStatus(OK)
    public City update(@PathVariable final Long cityId, @RequestBody City city) {
        final var citySaved = cityService.findById(cityId);

        try {
            BeanUtils.copyProperties(city, citySaved, "id");
            return cityService.save(citySaved);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final Long cityId) {
        cityService.delete(cityId);
    }
}
