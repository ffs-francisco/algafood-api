package com.ffs.algafood.api.controller.order;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ffs.algafood.api.model.request.order.OrderRequest;
import com.ffs.algafood.api.model.response.order.OrderResponse;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.User;
import com.ffs.algafood.domain.service.order.OrderService;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.ffs.algafood.api.model.response.order.OrderResponse.from;
import static com.ffs.algafood.api.model.response.order.OrderSummaryResponse.fromList;
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
    public MappingJacksonValue listAll(@RequestParam(required = false) final String fields) {
        final var orders = fromList(orderService.findAll());
        final var ordersMapper = new MappingJacksonValue(orders);

        final var filter = new SimpleFilterProvider();
        filter.addFilter("filterOrder", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.isNotBlank(fields)) {
            filter.addFilter("filterOrder", SimpleBeanPropertyFilter
                    .filterOutAllExcept(fields.replaceAll(" ", "").split(",")));
        }

        ordersMapper.setFilters(filter);
        return ordersMapper;
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
