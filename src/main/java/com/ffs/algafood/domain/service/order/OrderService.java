package com.ffs.algafood.domain.service.order;

import com.ffs.algafood.domain.exception.OrderNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.repository.order.OrderRepository;
import com.ffs.algafood.domain.repository.order.filter.OrderFilter;
import com.ffs.algafood.infrastructor.repositoty.specification.OrderSpecs;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Order> findAllByFilter(final OrderFilter filter) {
        return orderRepository.findAll(OrderSpecs.whitFilter(filter));
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
        this.findByCode(orderCode).confirm();
    }

    @Transactional
    public void cancel(final String orderCode) {
        this.findByCode(orderCode).cancel();
    }

    @Transactional
    public void delivered(final String orderCode) {
        this.findByCode(orderCode).delivered();
    }
}
