package com.kony.shopping.model.order;

import com.kony.shopping.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * CREATE TABLE OrderItem
 * (
 * itemid INT NOT NULL
 * ,pid INT NOT NULL
 * ,price INT
 * ,creationTime DATE
 * ,PRIMARY KEY(itemid)
 * ,FOREIGN KEY(pid) REFERENCES Product(pid)
 */
@Entity
@Data
@Table(schema = "shopping",name = "order_items")
@JsonIgnoreProperties({"order"})
public class OrderItem   {
        @Id
        @Column(name = "item_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer itemId;
        double price;
        Integer quantity;
        double discount;
        double tax;
        @Column(name = "create_at")
        LocalDateTime createAt = LocalDateTime.now();


        @ManyToOne
        @JoinColumn(name = "product_id")
        Product product;

        @ManyToOne
        @JoinColumn(name = "order_number")
        Order order;
}
