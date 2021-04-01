package com.ffs.algafood.infrastructor.repositoty.specification;

import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.repository.order.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 *
 * @author francisco
 */
public class OrderSpecs {

    public static Specification<Order> whitFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            if (query.getResultType().equals(Order.class)) {
                root.fetch("customer");
                root.fetch("paymentMethod");
                root.fetch("restaurant").fetch("kitchen");
            }

            final var predicates = new ArrayList<Predicate>();
            if (filter.getCustomerId() != null) {
                predicates.add(builder.equal(root.get("customer"), filter.getCustomerId()));
            }
            if (filter.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
            }
            if (filter.getDateRegisterStart() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateRegister"), filter.getDateRegisterStart()));
            }
            if (filter.getDateRegisterEnd() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateRegister"), filter.getDateRegisterEnd()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
