package com.kony.shopping.model.order;

import com.kony.shopping.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "shopping",name = "buyer")
public class Buyer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "buyer_id")
        Integer id;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        User user;

        @Column(name = "created_at")
        private LocalDate createdAt = LocalDate.now();

        @OneToMany(mappedBy = "buyer")
        List<Order> orders;

}
