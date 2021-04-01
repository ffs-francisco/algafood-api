package com.ffs.algafood.infrastructor.service.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;
import com.ffs.algafood.domain.service.projection.SaleProjectionReportService;
import com.ffs.algafood.domain.service.projection.SaleProjectionService;
import com.ffs.algafood.infrastructor.service.projection.exception.ProjectionException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author francisco
 */
@Service
public class SaleProjectionReportPDFServiceImpl implements SaleProjectionReportService {

    private final String JASPER_FILE = "/projections/daily-sales.jasper";

    @Autowired
    private SaleProjectionService saleProjectionService;

    @Override
    public byte[] findReportByFilter(DailySaleProjectionFilter filter, String offSet) {
        try {
            final var inputStream = getReportResource();
            final var parameters = getParametersReport();
            final var dataSource = getDataSourceReport(filter, offSet);

            final var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException ex) {
            throw new ProjectionException("Daily sales report could not be issued", ex);
        }
    }

    private JRBeanCollectionDataSource getDataSourceReport(DailySaleProjectionFilter filter, String offSet) {
        return new JRBeanCollectionDataSource(saleProjectionService.findByFilter(filter, offSet));
    }

    private InputStream getReportResource() {
        return this.getClass().getResourceAsStream(JASPER_FILE);
    }

    private HashMap<String, Object> getParametersReport() {
        final var parameters = new HashMap<String, Object>();
        parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

        return parameters;
    }
}
