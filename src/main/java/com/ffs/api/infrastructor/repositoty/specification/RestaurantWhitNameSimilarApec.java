package com.ffs.api.infrastructor.repositoty.specification;

import com.ffs.api.domain.model.Restaurant;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author francisco
 */
@AllArgsConstructor
public class RestaurantWhitNameSimilarApec implements Specification<Restaurant> {

    private final String name;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("name"), "%" + name + "%");
    }
}
