package com.ffs.algafood.domain.repository;

import com.ffs.algafood.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author francisco
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
