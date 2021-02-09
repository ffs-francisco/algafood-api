package com.ffs.algafood.domain.model.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author francisco
 */
@Getter
@Setter
@AllArgsConstructor
public class DailySaleProjection {

    private Date date;
    private Long totalSales;
    private BigDecimal totalBilled;
}
