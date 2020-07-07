package com.ffs.algafood.api.controller.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;
import com.ffs.algafood.domain.model.projection.DailySaleProjection;
import com.ffs.algafood.domain.service.projection.SaleProjectionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/projections")
public class SaleProjectionController {

    @Autowired
    private SaleProjectionService projectionService;

    @GetMapping("/daily-sales")
    public List<DailySaleProjection> findDailySales(final DailySaleProjectionFilter filter) {
        return projectionService.findByFilter(filter);
    }

}
