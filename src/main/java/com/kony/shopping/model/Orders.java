package com.kony.shopping.model;

import com.kony.shopping.model.order.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Orders{
    Integer orderNumber;

    LocalDate creationTime;
    Double totalAmount;
    List<OrderItems> orderItems;
}

