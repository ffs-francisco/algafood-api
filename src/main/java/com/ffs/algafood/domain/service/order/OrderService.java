package com.ffs.algafood.domain.service.order;

import com.ffs.algafood.domain.exception.OrderNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.repository.order.OrderRepository;
import com.ffs.algafood.domain.repository.order.filter.OrderFilter;
import com.ffs.algafood.infrastructor.repositoty.specification.OrderSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author francisco
 */
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderCreateUtil orderCreteUtil;

    public Page<Order> findAllByFilter(final OrderFilter filter, final Pageable pageable) {
        return orderRepository.findAll(OrderSpecs.whitFilter(filter), pageable);
    }

    public Order findByCode(final String orderCode) throws OrderNotFoundException {
        return orderRepository.findByCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException("code", orderCode));
    }

    @Transactional
    public Order issue(final Order order) throws EntityNotFoundException, BusinessException {
        return orderRepository.save(orderCreteUtil.create(order));
    }

    @Transactional
    public void confirm(final String orderCode) {
        final var order = this.findByCode(orderCode);
        order.confirm();

        orderRepository.save(order);
    }

    @Transactional
    public void cancel(final String orderCode) {
        final var order = this.findByCode(orderCode);
        order.cancel();

        orderRepository.save(order);
    }

    @Transactional
    public void delivered(final String orderCode) {
        this.findByCode(orderCode).delivered();
    }
}
