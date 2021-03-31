package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.controller.openapi.CityControllerOpenApi;
import com.ffs.algafood.api.model.request.city.CityRequest;
import com.ffs.algafood.api.model.response.city.CityResponse;
import com.ffs.algafood.domain.exception.StateNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author francisco
 */
@RestController
@RequestMapping(path = "/cities", produces = APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @GetMapping
    @ResponseStatus(OK)
    public List<CityResponse> listAll() {
        return CityResponse.fromList(cityService.findAll());
    }

    @GetMapping("/{cityId}")
    @ResponseStatus(OK)
    public CityResponse getById(
            @PathVariable final Long cityId
    ) {
        return CityResponse.from(cityService.findById(cityId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CityResponse add(
            @RequestBody @Valid final CityRequest cityRequest
    ) {
        try {
            return CityResponse.from(cityService.save(cityRequest.toModel()));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    @ResponseStatus(OK)
    public CityResponse update(
            @PathVariable final Long cityId,
            @RequestBody @Valid CityRequest cityRequest
    ) {
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
    public void delete(
            @PathVariable final Long cityId
    ) {
        cityService.delete(cityId);
    }
}
