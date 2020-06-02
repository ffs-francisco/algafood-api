package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.FormPaymentNotFoundException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.model.FormPayment;
import com.ffs.algafood.domain.repository.FormPaymentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class FormPaymentService {

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    public List<FormPayment> findAll() {
        return formPaymentRepository.findAll();
    }

    public FormPayment findById(final Long formPaymentId) {
        return formPaymentRepository.findById(formPaymentId)
                .orElseThrow(() -> new FormPaymentNotFoundException("id", formPaymentId));
    }

    @Transactional
    public FormPayment save(final FormPayment formPayment) {
        return formPaymentRepository.save(formPayment);
    }

    @Transactional
    public void delete(final Long formPaymentId) {
        try {
            formPaymentRepository.deleteById(formPaymentId);
        } catch (EmptyResultDataAccessException ex) {
            throw new FormPaymentNotFoundException("id", formPaymentId);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(formPaymentId, FormPayment.class);
        }
    }
}
