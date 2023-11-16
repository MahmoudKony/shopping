package com.kony.shopping.model.order;

import com.kony.shopping.product.ProductDTO;
import com.kony.shopping.product.ProductDTOMapper;
import com.kony.shopping.product.ProductRes;
import com.kony.shopping.product.ProductResMapper;

import java.time.LocalDateTime;
import java.util.function.Function;

record OrderItemDTO(

        Integer itemId,
        double price,
        Integer quantity,
        double discount,
        double tax,
        LocalDateTime createTime,
        ProductDTO product

) {

}

class OrderItemMapper implements Function<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO apply(OrderItem orderItem) {
        var order = orderItem.getOrder();

        var productMapper = new ProductDTOMapper();
        return new OrderItemDTO(
                orderItem.getItemId(),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                orderItem.getDiscount(),
                orderItem.getTax(),
                orderItem.getCreateAt(),
                productMapper.apply(orderItem.getProduct())
        );
    }
}

record OrderItemRes(
        Integer itemId,
        double price,
        Integer quantity,
        double discount,
        double tax,
        ProductRes product
) {
}

class OrderItemResMapper implements Function<OrderItemRes, OrderItem> {

    @Override
    public OrderItem apply(OrderItemRes orderItemRes) {

        var p = new ProductResMapper();


        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemRes.price());
        orderItem.setQuantity(orderItemRes.quantity());
        orderItem.setDiscount(orderItemRes.discount());
        orderItem.setProduct(p.apply(orderItemRes.product()));
        orderItem.setTax(orderItemRes.tax());
        return orderItem;
    }
}
