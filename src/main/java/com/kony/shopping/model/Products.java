package com.kony.shopping.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class Products {
    Integer productId;
    String name;
    String Type;
    double price;
    double amount;
}
@AllArgsConstructor
@Getter
@Setter
class Stores{
    Integer storeId;
    String name;
    String city;
    Date startTime;
    String streetAddress;
    List<Products> products;
}
