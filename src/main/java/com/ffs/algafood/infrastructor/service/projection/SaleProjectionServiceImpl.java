package com.ffs.algafood.infrastructor.service.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.model.projection.DailySaleProjection;
import com.ffs.algafood.domain.service.projection.SaleProjectionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francisco
 */
@Repository
public class SaleProjectionServiceImpl implements SaleProjectionService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySaleProjection> findByFilter(DailySaleProjectionFilter filter) {
        final var builder = manager.getCriteriaBuilder();
        final var query = builder.createQuery(DailySaleProjection.class);

        final var root = query.from(Order.class);

        final var dateFunction = builder.function("date", Date.class, root.get("dateRegister"));
        final var selection = builder.construct(DailySaleProjection.class,
                dateFunction,
                builder.count(root.get("id")),
                builder.sum(root.get("amount"))
        );

        query.where(getPredicates(filter, builder, root));
        query.select(selection);
        query.groupBy(dateFunction);

        return manager.createQuery(query).getResultList();
    }

    private Predicate[] getPredicates(final DailySaleProjectionFilter filter, final CriteriaBuilder builder, Root<Order> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }
        if (filter.getDateRegisterStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dateRegister"), filter.getDateRegisterStart()));
        }
        if (filter.getDateRegisterEnd() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dateRegister"), filter.getDateRegisterEnd()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
