package com.kony.shopping.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class OrderItems {
    Integer OrderItemId;
    Double price;
    LocalDate createTime;
    Integer quantity;
}
