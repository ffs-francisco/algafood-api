package com.ffs.algafood.domain.service.order;

import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.model.order.Order;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

import static com.ffs.algafood.domain.model.order.StatusOrderEnum.*;

/**
 *
 * @author francisco
 */
@Component
class OrderStatusFlowUtil {

    public void confirm(final Order order) throws BusinessException {
        if (!order.getStatus().equals(CREATED)) {
            throw new BusinessException(String.format("Order %d status can no longer be confirmed", order.getId()));
        }

        order.setStatus(CONFIRMED);
        order.setDateConfirmation(OffsetDateTime.now());
    }

    public void cancel(final Order order) throws BusinessException {
        if (!order.getStatus().equals(CREATED)) {
            throw new BusinessException(String.format("Order %d status can no longer be canceled", order.getId()));
        }

        order.setStatus(CANCELED);
        order.setDateCancellation(OffsetDateTime.now());
    }

    public void delivered(final Order order) throws BusinessException {
        if (!order.getStatus().equals(CONFIRMED)) {
            throw new BusinessException(String.format("Order %d status can no longer be delivered", order.getId()));
        }

        order.setStatus(DELIVERED);
        order.setDateDelivery(OffsetDateTime.now());
    }
}
