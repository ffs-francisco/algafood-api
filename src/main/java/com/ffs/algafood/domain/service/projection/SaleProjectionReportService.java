package com.ffs.algafood.domain.service.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;

/**
 *
 * @author francisco
 */
public interface SaleProjectionReportService {

    byte[] findReportByFilter(final DailySaleProjectionFilter filter, final String offSet);
}
