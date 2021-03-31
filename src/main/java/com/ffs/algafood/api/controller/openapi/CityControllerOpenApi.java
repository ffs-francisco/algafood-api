package com.ffs.algafood.api.controller.openapi;

import com.ffs.algafood.api.exception.model.ApiException;
import com.ffs.algafood.api.model.request.city.CityRequest;
import com.ffs.algafood.api.model.response.city.CityResponse;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("List all cities")
    List<CityResponse> listAll();

    @ApiOperation("Search city by ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID of city is invalid", response = ApiException.class),
            @ApiResponse(code = 404, message = "City not found", response = ApiException.class)
    })
    CityResponse getById(
            @ApiParam(value = "ID of a city", example = "1") final Long cityId
    );

    @ApiOperation("Register new city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City registered")
    })
    CityResponse add(
            @ApiParam(name = "body", value = "Representation of a new city") final CityRequest cityRequest
    );

    @ApiOperation("Update a city by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "City updated"),
            @ApiResponse(code = 404, message = "City not found", response = ApiException.class)
    })
    CityResponse update(
            @ApiParam(value = "ID of a city", example = "1") final Long cityId,
            @ApiParam(name = "body", value = "Representation of a city with a new data") CityRequest cityRequest
    );

    @ApiOperation("Delete a city by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "City deleted"),
            @ApiResponse(code = 404, message = "City not found", response = ApiException.class)
    })
    void delete(
            @ApiParam(value = "ID of a city", example = "1") final Long cityId
    );
}
