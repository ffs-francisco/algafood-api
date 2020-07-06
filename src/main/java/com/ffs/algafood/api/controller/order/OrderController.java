package com.ffs.algafood.api.controller.order;

import com.ffs.algafood.api.model.request.order.OrderRequest;
import com.ffs.algafood.api.model.response.order.OrderResponse;
import com.ffs.algafood.api.model.response.order.OrderSummaryResponse;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.User;
import com.ffs.algafood.domain.repository.order.filter.OrderFilter;
import com.ffs.algafood.domain.service.order.OrderService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.ffs.algafood.api.model.response.order.OrderResponse.from;
import static com.ffs.algafood.api.model.response.order.OrderSummaryResponse.fromPage;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(OK)
    public Page<OrderSummaryResponse> searchBy(@PageableDefault(size = 10) final Pageable pageable, final OrderFilter filter) {
        return fromPage(orderService.findAllByFilter(filter, pageable));
    }

    @GetMapping("/{orderCode}")
    @ResponseStatus(OK)
    public OrderResponse getById(@PathVariable final String orderCode) {
        return from(orderService.findByCode(orderCode));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderResponse add(@RequestBody @Valid final OrderRequest orderRequest) {
        try {
            final var newOrder = orderRequest.toModel();

            // TODO get authenticated user
            newOrder.setCustomer(new User());
            newOrder.getCustomer().setId(1L);

            return from(orderService.issue(newOrder));
        } catch (EntityNotFoundException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }
}
