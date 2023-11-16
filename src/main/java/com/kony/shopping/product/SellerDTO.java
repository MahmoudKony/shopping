package com.kony.shopping.product;

import com.kony.shopping.user.UserDTO;
import com.kony.shopping.user.UserDtoMapper;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

record SellerDTO(
        Integer sellerId,
        UserDTO user,
        List<StoreDTO> stores
){
}
class SellerDTOMapper implements Function<Seller,SellerDTO> {
    private  StoreDTOMapper dtoMapper = new StoreDTOMapper();
    private UserDtoMapper userDTOMapper = new UserDtoMapper();


    @Override
    public SellerDTO apply(Seller seller) {
        List<StoreDTO> list = new ArrayList<>();
        for (Store s: seller.getStores()) {
            list.add(dtoMapper.apply(s));
        }
        return new SellerDTO(
                seller.getSellerId(),
                userDTOMapper.apply(seller.getUser()),
                list
        );
    }
}
