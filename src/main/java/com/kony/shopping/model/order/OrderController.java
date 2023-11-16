package com.kony.shopping.model.order;


import com.kony.shopping.user.UserRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    List<OrderDTO> getOrder() {
        return orderService.getOrder();
    }


    @GetMapping("/buyers/{buyerId}/orders")
    ResponseEntity<List<OrderDTO>> getOrderByBuyer(@PathVariable int buyerId) {
        return ResponseEntity.ok(orderService.getOrderByBuyer(buyerId));
    }

    @PostMapping("/buyers/{buyerId}/orders/products/{productId}")
    void setOrderByBuyerId(@RequestBody OrderRes order,
                           @PathVariable @Validated Integer buyerId,
                           @PathVariable @Validated Integer productId) {
        System.out.println("^^^&&&&&&^^^  " + buyerId);
        orderService.setOrderByBuyerId(order, buyerId,productId);
    }

    @GetMapping("/orders/{orderId}")
    ResponseEntity<OrderDTO> getOrderById(@PathVariable int orderId) {
        return ResponseEntity.ok().body(orderService.getOrderById(orderId).get());
    }
    @GetMapping("orders/{orderNumber}/orderItems")
    List<OrderItemDTO> getOrderItem(@PathVariable int orderNumber) {
        return orderService.getOrderItem(orderNumber);
    }

    @PostMapping("order/{orderNumber}/orderItems")
    void setOrderItem(@RequestBody OrderItemRes orderItem, @PathVariable int orderNumber) {

        orderService.setOrderItem(orderNumber, orderItem);

    }

    @GetMapping("/orderItems/{orderItemId}")
    ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable int orderItemId) {
        return ResponseEntity.ok(orderService.getOrderItemById(orderItemId).get());
    }

    @GetMapping("/buyers")
    List<BuyerDTO> getBuyer() {
        return orderService.getBuyer();
    }

    @PostMapping("/buyers/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    void setBuyer(@PathVariable @Validated  int userId) {
        orderService.setBuyerWithUserId(userId);
    }

    @PostMapping("/buyer/user")
    void setBuyerWithUser(@RequestBody UserRes user){
        orderService.setBuyerWithUser(user);
    }

    @DeleteMapping("/orders")
    void deleteOrder(@RequestBody OrderRes order){
        orderService.deleteOrder(order);
    }

    @DeleteMapping("/buyers/{buyerId}")
     ResponseEntity<HttpStatus> deleteBuyerById(@PathVariable @Validated int buyerId){
       if ( orderService.deleteBuyerById(buyerId)){
           return ResponseEntity.ok().build();
       }else {
           return ResponseEntity.notFound().build();
       }
    }

    @GetMapping("buyer/{buyerId}")
    ResponseEntity<BuyerDTO> getBuyerId(int buyerId) {
        return ResponseEntity.ok(orderService.getBuyerId(buyerId).get());
    }
    @DeleteMapping("/orders/{orderId}")
    void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

    @DeleteMapping("/orderItems/{orderItemId}")
    void deleteOrderItem(@PathVariable int orderItemId){
        orderService.deleteOrderItem(orderItemId);
    }


}
