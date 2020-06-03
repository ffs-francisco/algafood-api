package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.city.CityRequest;
import com.ffs.algafood.api.model.response.city.CityResponse;
import com.ffs.algafood.domain.exception.StateNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.service.CityService;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping("/cities")
public class CityConstroller {

    @Autowired
    private CityService cityService;

    @GetMapping
    @ResponseStatus(OK)
    public List<CityResponse> listAll() {
        return CityResponse.fromList(cityService.findAll());
    }

    @GetMapping("/{cityId}")
    @ResponseStatus(OK)
    public CityResponse getById(@PathVariable final Long cityId) {
        return CityResponse.from(cityService.findById(cityId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CityResponse add(@RequestBody @Valid final CityRequest cityRequest) {
        try {
            return CityResponse.from(cityService.save(cityRequest.toModel()));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    @ResponseStatus(OK)
    public CityResponse update(@PathVariable final Long cityId, @RequestBody @Valid CityRequest cityRequest) {
        final var city = cityService.findById(cityId);

        try {
            cityRequest.copyPropertiesTo(city);
            return CityResponse.from(cityService.save(city));
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
