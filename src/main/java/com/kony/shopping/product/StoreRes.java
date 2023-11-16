package com.kony.shopping.product;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

record StoreDTO(
        Integer storeId,
        String StoreName,
        String description,
        LocalDate createAt,
        List<ProductDTO> products
){ }
class StoreDTOMapper implements Function<Store, StoreDTO> {


    @Override
    public StoreDTO apply(Store store) {

        var p =new ProductDTOMapper();
        var pd = store.getProducts().stream().map(p).toList();
        return new StoreDTO(
                store.getStoreId(),
                store.getName(),
                store.getDescription(),
                store.getCreateAt(),
                pd
        );
    }
}

public record StoreRes(
        String StoreName,
        String description,
        List<ProductRes> products
)implements Serializable {}
