package com.ffs.algafood.api.controller.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;
import com.ffs.algafood.domain.model.projection.DailySaleProjection;
import com.ffs.algafood.domain.service.projection.SaleProjectionReportService;
import com.ffs.algafood.domain.service.projection.SaleProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/projections")
public class SaleProjectionController {

    @Autowired
    private SaleProjectionService projectionService;

    @Autowired
    private SaleProjectionReportService projectionReportService;

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySaleProjection> findDailySales(
            final DailySaleProjectionFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") final String offSet
    ) {
        return projectionService.findByFilter(filter, offSet);
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findDailySalesPDF(
            final DailySaleProjectionFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") final String offSet
    ) {
        final var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attchment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(APPLICATION_PDF)
                .body(projectionReportService.findReportByFilter(filter, offSet));
    }
}
