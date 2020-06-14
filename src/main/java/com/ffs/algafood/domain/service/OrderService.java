package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.OrderNotFoundException;
import com.ffs.algafood.domain.exception.ProductNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.order.Order;
import com.ffs.algafood.domain.model.order.OrderItem;
import com.ffs.algafood.domain.model.restaurant.PaymentMethod;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.OrderRepository;
import com.ffs.algafood.domain.service.restaurant.PaymentMethodService;
import com.ffs.algafood.domain.service.restaurant.ProductService;
import com.ffs.algafood.domain.service.restaurant.RestaurantService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;

/**
 *
 * @author francisco
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PaymentMethodService paymentMethodService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("id", orderId));
    }

    @Transactional
    public Order issue(final Order order) throws EntityNotFoundException, BusinessException {
        this.validate(order);
        this.validateItens(order);

        order.setShippingFee(order.getRestaurant().getShippingFee());
        this.calculateTotalValues(order);

        return orderRepository.save(order);
    }

    private void validate(final Order order) throws EntityNotFoundException, BusinessException {
        final var customer = userService.findById(order.getCustomer().getId());
        final var restaurant = restaurantService.findById(order.getRestaurant().getId());
        final var city = cityService.findById(order.getAddressDelivery().getCity().getId());
        final var paymentMethod = paymentMethodService.findById(order.getPaymentMethod().getId());

        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.getAddressDelivery().setCity(city);
        order.setPaymentMethod(paymentMethod);

        this.validatePaymentMethod(restaurant, paymentMethod);
    }

    private void validatePaymentMethod(Restaurant restaurant, PaymentMethod paymentMethod) throws BusinessException {
        if (!restaurant.getPaymentMethods().contains(paymentMethod)) {
            throw new BusinessException(format("This restaurant not accept the payment wiht %s", paymentMethod.getDescription()));
        }
    }

    private void validateItens(final Order order) throws ProductNotFoundException {
        order.getItens().forEach(item -> {
            final var product = productService.findAByRestaurant(order.getRestaurant().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setPriceUnit(product.getPrice());
        });
    }

    private void calculateTotalValues(final Order order) {
        order.getItens().forEach(this::calculateTotalItemPrice);

        final var subTotal = order.getItens().stream()
                .map(item -> item.getPriceAmount())
                .reduce(ZERO, BigDecimal::add);

        order.setSubTotal(subTotal);
        order.setAmount(subTotal.add(order.getShippingFee()));
    }

    void calculateTotalItemPrice(final OrderItem item) {
        final var priceUnit = (item.getPriceUnit() == null) ? ZERO : item.getPriceUnit();
        final var quantity = (item.getQuantity() == null) ? 0 : item.getQuantity();

        item.setPriceAmount(priceUnit.multiply(new BigDecimal(quantity)));
    }
}
