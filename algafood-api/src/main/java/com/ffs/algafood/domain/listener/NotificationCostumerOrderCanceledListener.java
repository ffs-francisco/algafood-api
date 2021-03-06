package com.ffs.algafood.domain.listener;

import com.ffs.algafood.domain.event.OrderCanceledEvent;
import com.ffs.algafood.domain.service.mail.SendEmailService;
import com.ffs.algafood.domain.service.mail.SendEmailService.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@AllArgsConstructor
public class NotificationCostumerOrderCanceledListener {

    private final SendEmailService emailService;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void whenConfirmingOrder(final OrderCanceledEvent event) {
        final var order = event.getOrder();
        final var message = Message.builder()
                .recipient(order.getCustomer().getEmail())
                .subject(order.getRestaurant().getName() + " - Confirmação de Pedido")
                .body("order-cancellation.html")
                .variable("order", order)
                .build();

        this.emailService.send(message);
    }
}
