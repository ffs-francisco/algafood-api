package com.ffs.algafood.domain.service.order;

import com.ffs.algafood.domain.exception.OrderNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.repository.OrderRepository;
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
    @Autowired
    private OrderStatusFlowUtil statusFlowUtil;

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
        statusFlowUtil.confirm(this.findById(orderId));
    }

    @Transactional
    public void cancel(final Long orderId) {
        statusFlowUtil.cancel(this.findById(orderId));
    }

    @Transactional
    public void delivered(final Long orderId) {
        statusFlowUtil.delivered(this.findById(orderId));
    }
}
