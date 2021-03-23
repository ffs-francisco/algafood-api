package com.ffs.algafood.domain.service.restaurant;

import com.ffs.algafood.domain.exception.PaymentMethodNotFoundException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.model.restaurant.PaymentMethod;
import com.ffs.algafood.domain.repository.restaurant.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author francisco
 */
@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(final Long paymentId) {
        return paymentMethodRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentMethodNotFoundException("id", paymentId));
    }

    public OffsetDateTime findLatestUpdatedDate() {
        return paymentMethodRepository.findLatestUpdatedDate();
    }

    @Transactional
    public PaymentMethod save(final PaymentMethod payment) {
        return paymentMethodRepository.save(payment);
    }

    @Transactional
    public void delete(final Long paymentId) {
        try {
            paymentMethodRepository.deleteById(paymentId);
            paymentMethodRepository.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new PaymentMethodNotFoundException("id", paymentId);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(paymentId, PaymentMethod.class);
        }
    }
}
