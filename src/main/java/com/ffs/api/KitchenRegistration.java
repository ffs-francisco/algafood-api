package com.ffs.api;

import com.ffs.api.model.Kitchen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
public class KitchenRegistration {

    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> list() {
        return this.manager.createQuery("FROM Kitchen", Kitchen.class).getResultList();
    }

}
