package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.FormPayment;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface FormPaymentRepository {

    List<FormPayment> findAll();

    FormPayment findById(Long id);

    FormPayment save(final FormPayment city);

    void delete(final FormPayment formPayment);
}
