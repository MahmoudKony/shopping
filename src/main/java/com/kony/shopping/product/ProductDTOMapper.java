package com.kony.shopping.product;

import java.util.function.Function;

public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.productId,
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.price,
                product.getAmount()
        );
    }
}
