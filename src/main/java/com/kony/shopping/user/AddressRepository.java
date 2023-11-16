package com.kony.shopping.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    List<Address> findAllByUser(User user);
}
