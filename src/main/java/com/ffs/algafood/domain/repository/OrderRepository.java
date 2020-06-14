package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.order.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author francisco
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("FROM Order O JOIN FETCH O.customer JOIN FETCH O.restaurant R JOIN FETCH R.kitchen")
    List<Order> findAll();

    Optional<Order> findByCode(String code);
}
