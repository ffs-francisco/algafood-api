package com.ffs.api.infrastructor.repositoty;

import com.ffs.api.domain.repository.RestaurantRepositoryCustom;
import com.ffs.api.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal) {
        final var jpql = "FROM Restaurant WHERE name LIKE :name "
                + "AND shippingFee BETWEEN :shippingFeeInitial AND :shippingFeeFinal";

        return entityManager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("shippingFeeInitial", shippingFeeInitial)
                .setParameter("shippingFeeFinal", shippingFeeFinal)
                .getResultList();
    }
}
