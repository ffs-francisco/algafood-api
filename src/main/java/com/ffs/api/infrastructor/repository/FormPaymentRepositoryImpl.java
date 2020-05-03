package com.ffs.api.infrastructor.repository;

import com.ffs.api.domain.model.FormPayment;
import com.ffs.api.domain.repository.FormPaymentRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Component
public class FormPaymentRepositoryImpl implements FormPaymentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public FormPayment save(final FormPayment formPayment) {
        return this.manager.merge(formPayment);
    }

    @Override
    @Transactional
    public void delete(final FormPayment formPayment) {
        this.manager.remove(this.findById(formPayment.getId()));
    }

    @Override
    public List<FormPayment> findAll() {
        return this.manager.createQuery("FROM FormPayment", FormPayment.class).getResultList();
    }

    @Override
    public FormPayment findById(Long id) {
        return this.manager.find(FormPayment.class, id);
    }
}
