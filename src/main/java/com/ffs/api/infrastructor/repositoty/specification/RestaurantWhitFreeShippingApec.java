package com.ffs.api.infrastructor.repositoty.specification;

import com.ffs.api.domain.model.Restaurant;
import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author francisco
 */
public class RestaurantWhitFreeShippingApec implements Specification<Restaurant> {

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }
}
