package com.kony.shopping.product;

import java.time.LocalDate;


record StoreRequest(

        Integer storeId,
        String StoreName,
        String description,
        LocalDate createAt,
        Seller seller
) {
}

