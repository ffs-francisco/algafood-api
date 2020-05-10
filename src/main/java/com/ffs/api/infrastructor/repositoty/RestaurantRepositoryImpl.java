package com.ffs.api.infrastructor.repositoty;

import com.ffs.api.domain.repository.RestaurantRepositoryCustom;
import com.ffs.api.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
        final var jpql = new StringBuilder();
        final var parameters = new HashMap<String, Object>();

        jpql.append("FROM Restaurant WHERE 0 = 0 ");
        if (StringUtils.hasLength(name)) {
            jpql.append("AND name LIKE :name ");
            parameters.put("name", "%" + name + "%");

        }

        if (shippingFeeInitial != null) {
            jpql.append("AND shippingFee >= :shippingFeeInitial ");
            parameters.put("shippingFeeInitial", shippingFeeInitial);
        }

        if (shippingFeeFinal != null) {
            jpql.append("AND shippingFee <= :shippingFeeFinal ");
            parameters.put("shippingFeeFinal", shippingFeeFinal);
        }

        TypedQuery<Restaurant> query = entityManager.createQuery(jpql.toString(), Restaurant.class);
        parameters.forEach((param, value) -> query.setParameter(param, value));

        return query.getResultList();
    }
}
