package com.kony.shopping.product;

import com.kony.shopping.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "shopping", name = "seller")
public class Seller {
    @OneToMany(mappedBy = "seller")
    List<Store> stores;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Integer sellerId;
    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
