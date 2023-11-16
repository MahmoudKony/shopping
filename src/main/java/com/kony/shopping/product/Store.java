package com.kony.shopping.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "shopping",name = "stores")
public class Store  implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "store_id")
        Integer storeId;
        @Column(nullable = false)
        String name;
        String description;
        @Column(name = "create_at")
        LocalDate createAt ;
        @ManyToOne
        @JoinColumn(name = "seller_id")
        Seller seller;

        @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
        List<Product>products;

}
