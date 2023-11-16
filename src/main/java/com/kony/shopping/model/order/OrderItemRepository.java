package com.kony.shopping.model.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findAllByOrder(Order order);
}
