package com.kony.shopping.product;

import com.kony.shopping.model.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * pid INT NOT NULL
 * ,sid INT NOT NULL
 * ,brand VARCHAR(50) NOT NULL
 * ,name VARCHAR(100)
 * ,type VARCHAR(50)
 * ,modelNumber VARCHAR(50)
 * ,color VARCHAR(50)
 * ,amount INT
 * ,price INT
 * ,PRIMARY KEY(pid)
 * ,FOREIGN KEY(sid) REFERENCES Store(sid)
 * ,FOREIGN KEY(brand) REFERENCES Brand(brandName)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "shopping",name = "products")

public class Product implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        Integer productId;
        @Column(nullable = false)
        String name;
        String description;
        String image;
        @Column(nullable = false)
        double price;
        @Column(name = "created_at")
        LocalDateTime createdAt = LocalDateTime.now();
        @Column(nullable = false)
        double amount;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "store_id")
        Store store;

        @OneToMany(mappedBy = "product")
        List<OrderItem> orderItems;
}
