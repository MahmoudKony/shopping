package com.kony.shopping.product;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.function.Function;

public record ProductDTO(
        Integer productId,
        String name,
        String description,
        String image ,
        double price,
        double amount
) {
}

