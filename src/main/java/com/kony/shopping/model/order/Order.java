package com.kony.shopping.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CREATE TABLE Orders
 * (
 * orderNumber INT NOT NULL
 * ,paymentState VARCHAR(12)
 * ,creationTime DATE
 * ,totalAmount INT
 * ,PRIMARY KEY (orderNumber)
 * );
 */

@Entity
@Getter
@Setter
@Table(schema = "shopping",name = "orders")
public class Order {
        @Id
        @Column(name = "order_number")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int orderNumber;

        @Column(name = "total_price")
        double totalPrice;
        @Column(name = "create_at")
        LocalDateTime createAt;
        @Column(name = "order_status")
        @Enumerated(EnumType.STRING)
        OrderStatus status;
        @OneToMany(mappedBy = "order")
        List<OrderItem> orderItems;

        @ManyToOne
        @JoinColumn(name = "buyer_id")
        @JsonIgnore
        @NotNull
        Buyer buyer;


}

enum OrderStatus{

        PENDING,
        SHIPPED,
        DELIVERED,
        PROCESSING,
        CANCELED,
        RETURNED
}