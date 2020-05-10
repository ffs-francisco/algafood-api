package com.ffs.api.infrastructor.repositoty;

import com.ffs.api.domain.repository.RestaurantRepositoryCustom;
import com.ffs.api.domain.model.Restaurant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteriaQuery = criteriaBuilder.createQuery(Restaurant.class);
        
        Root<Restaurant> root = criteriaQuery.from(Restaurant.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasLength(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (shippingFeeInitial != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("shippingFee"), shippingFeeInitial));
        }
        if (shippingFeeFinal != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("shippingFee"), shippingFeeFinal));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }
}
