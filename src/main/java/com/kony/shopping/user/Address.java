package com.kony.shopping.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(schema = "shopping",name = "address")
public class Address implements Serializable  {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "address_id")
        Integer addressId;
        @Column(nullable = false)
        String state;
        @Column(name = "contact_phone_number")
        String contactPhoneNumber;
        @Column(nullable = false)
        String city;
        @Column(name = "street",nullable = false)
        String street;
        @Column(name = "post_code",nullable = false)
        String postCode;
        //my add
        LocalDate created_at = LocalDate.now();

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        User user;

        @Override
        public String toString() {
                return "Address{" +
                        "addressId=" + addressId +
                        ", state='" + state + '\'' +
                        '}';
        }
}
