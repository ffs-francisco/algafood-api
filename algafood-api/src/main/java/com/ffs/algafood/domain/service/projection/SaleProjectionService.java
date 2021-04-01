package com.ffs.algafood.domain.service.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;
import com.ffs.algafood.domain.model.projection.DailySaleProjection;

import java.util.List;

/**
 *
 * @author francisco
 */
public interface SaleProjectionService {

    List<DailySaleProjection> findByFilter(final DailySaleProjectionFilter filter, final String offSet);
}
