package com.ffs.algafood.domain.service.order;

import com.ffs.algafood.domain.exception.OrderNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.repository.order.OrderRepository;
import com.ffs.algafood.domain.repository.order.filter.OrderFilter;
import com.ffs.algafood.domain.service.mail.SendEmailService;
import com.ffs.algafood.infrastructor.repositoty.specification.OrderSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ffs.algafood.domain.service.mail.SendEmailService.Message;

/**
 * @author francisco
 */
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderCreateUtil orderCreteUtil;
    private final SendEmailService emailService;

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

        final var message = Message.builder()
                .recipient(order.getCustomer().getEmail())
                .subject(order.getRestaurant().getName() + " - Confirmação de Pedido")
                .body(String.format("O pedido de código <strong> %s </strong> for confirmado!", order.getCode()))
                .build();

        this.emailService.send(message);
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
