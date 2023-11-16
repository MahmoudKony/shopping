package com.kony.shopping.product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StoreResMapper implements Function<StoreRes, Store> {
    @Override
    public Store apply(StoreRes storeRes) {
        var store = new Store();

        var s = new ProductResMapper();
        List<Product> products = new ArrayList<>();
        storeRes.products().forEach(
                st -> {
                    System.out.println(":>>>" + s.apply(st));
                    var o = s.apply(st);
                    o.setStore(store);
                    products.add(o);
                }
        );
        store.setName(storeRes.StoreName());
        store.setDescription(storeRes.description());
        store.setProducts(products);

        return store;
    }
}
