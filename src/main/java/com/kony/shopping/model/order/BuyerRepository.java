package com.kony.shopping.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
}
