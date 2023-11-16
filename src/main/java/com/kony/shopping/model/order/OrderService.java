package com.kony.shopping.model.order;

import com.kony.shopping.product.ProductRepository;
import com.kony.shopping.product.ProductResMapper;
import com.kony.shopping.user.UserRepository;
import com.kony.shopping.user.UserRes;
import com.kony.shopping.user.UserResMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository itemRepository;
    private final BuyerRepository buyerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderResMapper orderResMapper = new OrderResMapper();

    private final OrderMapper orderMapper = new OrderMapper();
    private final OrderItemMapper orderItemMapper = new OrderItemMapper();
    private final BuyerDTOMapper buyerDTOMapper = new BuyerDTOMapper();
    private final OrderItemResMapper itemResMapper = new OrderItemResMapper();
    private final ProductResMapper productResMapper = new ProductResMapper();
    private final UserResMapper userResMapper = new UserResMapper();

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository itemRepository,
                        BuyerRepository buyerRepository,
                        UserRepository userRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    List<OrderDTO> getOrder() {
        return orderRepository.findAll().stream().map(
                orderMapper
        ).collect(Collectors.toList());
    }

    List<OrderDTO> getOrderByBuyer(int buyerId) {

        return buyerRepository.findById(buyerId)
                .map(
                        b -> {
                            return orderRepository.findAllByBuyer(b).stream().map(
                                    orderMapper
                            ).toList();
                        }).orElseThrow();
    }

    Optional<OrderDTO> getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper);
    }

    List<OrderItemDTO> getOrderItem(int orderNumber) {
        return itemRepository.findAllByOrder(
                orderRepository.findById(orderNumber).get()
        ).stream().map(orderItemMapper).toList();
    }

    Optional<OrderItemDTO> getOrderItemById(int id) {
        return itemRepository.findById(id).map(orderItemMapper);
    }

    List<BuyerDTO> getBuyer() {
        return buyerRepository.findAll()
                .stream().map(buyerDTOMapper).collect(Collectors.toList());

    }

    Optional<BuyerDTO> getBuyerId(int buyerId) {
        return buyerRepository.findById(buyerId).map(buyerDTOMapper);
    }

    public void setBuyerWithUserId(int userId) {


        var buyer = new Buyer();
        userRepository.findById(userId).map(
                user -> {
                    buyer.setUser(user);
                    buyerRepository.save(buyer);
                    return "";
                }
        );
    }

    void setBuyerWithUser(UserRes userRes) {
        var user = userResMapper.apply(userRes);
        userRepository.save(user);
        var buyer = new Buyer();
        buyer.setUser(user);
        buyerRepository.save(buyer);
    }


    void setOrderByBuyerId(OrderRes orderRes,
                           Integer buyerId,
                           Integer productId
    ) {
        var order = orderResMapper.apply(orderRes);


        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()){
            buyer.get();
            buyer.map(
                    b -> {
                        System.out.println(b.getId() + "*******");
                        order.setBuyer(b);
                        double totalAmount = 0.0;
                        if (!order.getOrderItems().isEmpty()) {
                            for (OrderItem uu : order.getOrderItems()) {
                                if (productRepository.existsById(productId)) {
                                    var p = productRepository.findById(1).get();
                                    uu.setProduct(p);
                                    uu.setPrice(uu.getProduct().getPrice());
                                    var am = uu.getPrice() * uu.getQuantity();
                                    System.out.println("$$$$$$$$$$$$$$$" + am);
                                    totalAmount += am;
                                } else {
                                    throw new RuntimeException("product id no fount");
                                }

                            }

                        } else {
                           throw new RuntimeException("no order item fount ");

                        }
                        order.setTotalPrice(totalAmount);
                        orderRepository.save(order);
                        order.getOrderItems().forEach(
                                m -> {
                                    m.setProduct(productRepository.findById(m.getProduct().getProductId()).get());
                                    productRepository.save(m.getProduct());
                                    m.setOrder(order);
                                    m.setPrice(m.getProduct().getPrice());
                                    System.out.println("$$$$" + m.getOrder().getOrderNumber());
                                    itemRepository.save(m);

                                }
                        );
                        return "";
                    }
            );
        }else {
            throw new RuntimeException("user Id not font");
        }
    }

    void setOrderItem(@PathVariable int orderNumber,
                      OrderItemRes orderItemRes) {
        OrderItem orderItem = itemResMapper.apply(orderItemRes);
        orderRepository.findById(orderNumber).map(
                o -> {
                    orderItem.setPrice(orderItem.getProduct().getPrice() * orderItem.getQuantity());
                    itemRepository.save(orderItem);

                    orderItem.setOrder(o);
                    var totalAmount = 0.0;
                    var allitem = itemRepository.findAllByOrder(o);
                    for (OrderItem uu : allitem) {
                        var am = uu.getPrice() * uu.getQuantity();
                        totalAmount += am;
                    }
                    o.setTotalPrice(totalAmount);
                    orderRepository.save(o);
                    return "";
                }
        );


    }

    void deleteOrder(OrderRes order) {
        orderResMapper.apply(order);
        orderRepository.delete(orderResMapper.apply(order));
    }

    void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    void deleteOrderItem(int id) {
        itemRepository.findById(id).map(
                orderItem -> {
                    var order = orderItem.getOrder();
                    var total = order.getTotalPrice() - orderItem.getPrice();
                    order.setTotalPrice(total);
                    orderRepository.save(order);
                    return "";
                }
        );
        itemRepository.deleteById(id);

    }


    Boolean deleteBuyerById(int id) {
        if (buyerRepository.existsById(id)) {
            buyerRepository.deleteById(id);
            return true;
        } else return false;
    }

    public void deleteBuyer(Buyer buyer) {

        buyerRepository.delete(buyer);
    }
}
