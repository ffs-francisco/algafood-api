package com.ffs.api.jpa;

import com.ffs.api.model.Kitchen;
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
public class KitchenRegistration {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Transactional
    public Kitchen save(final Kitchen kitchen) {
        return this.manager.merge(kitchen);
    }
    
    @Transactional
    public void delete(final Kitchen kitchen) {
        this.manager.remove(this.getById(kitchen.getId()));
    }
    
    public List<Kitchen> list() {
        return this.manager.createQuery("FROM Kitchen", Kitchen.class).getResultList();
    }
    
    public Kitchen getById(Long id) {
        return this.manager.find(Kitchen.class, id);
    }
}
