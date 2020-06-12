package com.ffs.algafood.domain.service.restaurant;

import com.ffs.algafood.domain.exception.RestaurantNotFoundException;
import com.ffs.algafood.domain.exception.base.BusinessException;
import com.ffs.algafood.domain.exception.base.EntityNotFoundException;
import com.ffs.algafood.domain.model.restaurant.Restaurant;
import com.ffs.algafood.domain.repository.restaurant.RestaurantRepository;
import com.ffs.algafood.domain.service.CityService;
import com.ffs.algafood.domain.service.KitchenService;
import com.ffs.algafood.domain.service.PaymentMethodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenService kitchenService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PaymentMethodService paymentMethodService;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(final Long restaurantId) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("id", restaurantId));
    }

    @Transactional
    public Restaurant save(final Restaurant restaurant) throws BusinessException {
        try {
            final var kitchen = kitchenService.findById(restaurant.getKitchen().getId());
            final var city = cityService.findById(restaurant.getAddress().getCity().getId());

            restaurant.setKitchen(kitchen);
            restaurant.getAddress().setCity(city);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }

        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void activate(final Long restauranteId) {
        this.findById(restauranteId).setActive(Boolean.TRUE);
    }

    @Transactional
    public void inactivate(final Long restauranteId) {
        this.findById(restauranteId).setActive(Boolean.FALSE);
    }

    @Transactional
    public void opening(final Long restauranteId) {
        this.findById(restauranteId).setOpen(Boolean.TRUE);
    }

    @Transactional
    public void closing(final Long restauranteId) {
        this.findById(restauranteId).setOpen(Boolean.FALSE);
    }

    @Transactional
    public void unlinkPaymentMethod(final Long restaurantId, final Long paymentMethodId) {
        final var restaurant = this.findById(restaurantId);
        final var paymentMethod = paymentMethodService.findById(paymentMethodId);

        restaurant.getPaymentMethods().remove(paymentMethod);
    }

    @Transactional
    public void linkPaymentMethod(final Long restaurantId, final Long paymentMethodId) {
        final var restaurant = this.findById(restaurantId);
        final var paymentMethod = paymentMethodService.findById(paymentMethodId);

        restaurant.getPaymentMethods().add(paymentMethod);
    }
}
