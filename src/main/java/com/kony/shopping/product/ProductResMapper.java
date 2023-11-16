package com.kony.shopping.product;

import java.util.function.Function;

public class ProductResMapper implements Function<ProductRes, Product> {

    @Override
    public Product apply(ProductRes productRes) {
        var product = new Product();
        var s = new  StoreResMapper();
        product.setName(productRes.name());
        product.setDescription(productRes.description());
        product.setImage(productRes.image());
        product.setPrice(productRes.price());
        product.setAmount(productRes.amount());

        return product;
    }
}
