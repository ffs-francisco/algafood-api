package com.ffs.algafood.domain.service.order;

import com.ffs.algafood.domain.exception.OrderNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.repository.OrderRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ffs.algafood.domain.model.order.StatusOrderEnum.CONFIRMED;
import static com.ffs.algafood.domain.model.order.StatusOrderEnum.CREATED;

/**
 *
 * @author francisco
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCreateUtil orderCreteUtil;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("id", orderId));
    }

    @Transactional
    public Order issue(final Order order) throws EntityNotFoundException, BusinessException {
        return orderRepository.save(orderCreteUtil.create(order));
    }

    @Transactional
    public void confirm(final Long orderId) {
        final var order = this.findById(orderId);

        if (!order.getStatus().equals(CREATED)) {
            throw new BusinessException(String.format("Order status %d cannot be changed from %s to %s",
                    order.getId(), order.getStatus().getDescription(), CONFIRMED.getDescription()));
        }

        order.setStatus(CONFIRMED);
        order.setDateConfirmation(OffsetDateTime.now());
    }

}
