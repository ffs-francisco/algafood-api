package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.city.CityRequest;
import com.ffs.algafood.api.model.response.city.CityResponse;
import com.ffs.algafood.domain.exception.StateNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author francisco
 */
@Api(tags = "Cities")
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @ApiOperation("List all cities")
    @GetMapping
    @ResponseStatus(OK)
    public List<CityResponse> listAll() {
        return CityResponse.fromList(cityService.findAll());
    }

    @ApiOperation("Search city by ID")
    @GetMapping("/{cityId}")
    @ResponseStatus(OK)
    public CityResponse getById(
            @ApiParam(value = "ID of a city", example = "1")
            @PathVariable final Long cityId
    ) {
        return CityResponse.from(cityService.findById(cityId));
    }

    @ApiOperation("Register new city")
    @PostMapping
    @ResponseStatus(CREATED)
    public CityResponse add(
            @ApiParam(name = "body", value = "Representation of a new city")
            @RequestBody @Valid final CityRequest cityRequest
    ) {
        try {
            return CityResponse.from(cityService.save(cityRequest.toModel()));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @ApiOperation("Update a city by ID")
    @PutMapping("/{cityId}")
    @ResponseStatus(OK)
    public CityResponse update(
            @ApiParam(value = "ID of a city", example = "1")
            @PathVariable final Long cityId,

            @ApiParam(name = "body", value = "Representation of a city with a new datas")
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

    @ApiOperation("Delete a city by ID")
    @DeleteMapping("/{cityId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(
            @ApiParam(value = "ID of a city", example = "1")
            @PathVariable final Long cityId
    ) {
        cityService.delete(cityId);
    }
}
