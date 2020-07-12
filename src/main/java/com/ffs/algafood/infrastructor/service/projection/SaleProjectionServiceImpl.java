package com.ffs.algafood.infrastructor.service.projection;

import com.ffs.algafood.domain.filter.DailySaleProjectionFilter;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.model.order.StatusOrderEnum;
import com.ffs.algafood.domain.model.projection.DailySaleProjection;
import com.ffs.algafood.domain.service.projection.SaleProjectionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
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
    public List<DailySaleProjection> findByFilter(DailySaleProjectionFilter filter, final String offSet) {
        final var builder = manager.getCriteriaBuilder();
        final var query = builder.createQuery(DailySaleProjection.class);

        final var root = query.from(Order.class);

        final var dateExpression = this.getExpressionDate(builder, root, offSet);
        final var selection = builder.construct(DailySaleProjection.class,
                dateExpression,
                builder.count(root.get("id")),
                builder.sum(root.get("amount"))
        );

        query.where(this.getPredicates(filter, builder, root));
        query.select(selection);
        query.groupBy(dateExpression);

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
        predicates.add(root.get("status").in(StatusOrderEnum.CONFIRMED, StatusOrderEnum.DELIVERED));

        return predicates.toArray(new Predicate[0]);
    }

    private Expression<Date> getExpressionDate(final CriteriaBuilder builder, final Root<Order> root, final String offSet) {
        return builder.function("date", Date.class, builder.function(
                "convert_tz", Date.class, root.get("dateRegister"), builder.literal("+00:00"), builder.literal(offSet)
        ));
    }
}
