package com.ffs.algafood.core.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Pageable")
public class PageableModelOpenApi {

    @ApiModelProperty(example = "0", value = "Number of page, init in 0")
    private int page;

    @ApiModelProperty(example = "10", value = "The amount of elements by page")
    private int size;

    @ApiModelProperty(example = "name,asc", value = "Name of property for ordination")
    private List<String> sort;
}
