package com.ffs.algafood.domain.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

/**
 *
 * @author francisco
 */
@Getter
@Setter
public class DailySaleProjectionFilter {

    private Long restaurantId;

    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dateRegisterStart;

    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dateRegisterEnd;
}
