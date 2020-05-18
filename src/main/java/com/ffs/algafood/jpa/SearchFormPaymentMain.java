package com.ffs.algafood.jpa;

import com.ffs.algafood.ApiApplication;
import com.ffs.algafood.domain.repository.FormPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static java.lang.String.format;

/**
 *
 * @author francisco
 */
@Slf4j
public class SearchFormPaymentMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var formPayment = appContext.getBean(FormPaymentRepository.class);

        log.info("Todas as formas de pagamento");
        formPayment.findAll().forEach(payment -> {
            log.info(format(payment.getDescription()));
        });
    }
}
