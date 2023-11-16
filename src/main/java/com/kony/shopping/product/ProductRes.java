package com.kony.shopping.product;

import java.io.Serializable;

public record ProductRes(
        String name,
        String description,
        String image,
        double price,
        double amount
) implements Serializable {
}
