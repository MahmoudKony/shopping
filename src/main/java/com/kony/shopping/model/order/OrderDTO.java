package com.kony.shopping.model.order;

import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


record OrderDTO(
        int orderNumber,
        double totalPrice,
        LocalDateTime createAt,
        OrderStatus status,
        List<OrderItemDTO> orderItems


) {
}
class OrderMapper implements Function<Order, OrderDTO> {

    @Override
    public OrderDTO apply(Order order) {
        var item = order.getOrderItems();
        List<OrderItemDTO> itemDTOS = new ArrayList<>();
        var mapper = new OrderItemMapper();
        for (OrderItem o: order.getOrderItems()) {
            itemDTOS.add(mapper.apply(o));

        }

        return new OrderDTO(
                order.getOrderNumber(),
                order.getTotalPrice(),
                order.getCreateAt(),
                order.getStatus(),
                itemDTOS

        );
    }
}

record OrderRes(
        OrderStatus status,
        List<OrderItemRes> OrderItem

)implements Serializable {}
class OrderResMapper implements Function<OrderRes,Order>{
    @Override
    public Order apply(OrderRes orderRes) {
        OrderItemResMapper itemResMapper = new OrderItemResMapper();
        Order order = new Order();
        order.setStatus(orderRes.status());
        order.setOrderItems(orderRes.OrderItem().
                stream().map(itemResMapper).collect(Collectors.toList()));


        return order;
    }
}
