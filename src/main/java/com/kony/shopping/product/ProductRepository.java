package com.kony.shopping.product;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByStore(Store store);

//    Store findStore(Store store);
}
