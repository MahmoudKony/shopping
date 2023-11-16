package com.kony.shopping.model.order;

import com.kony.shopping.user.UserDTO;
import com.kony.shopping.user.UserDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

record BuyerDTO(
        Integer id,
        UserDTO user,
        List<OrderDTO> orders
) {
}
class BuyerDTOMapper implements Function<Buyer,BuyerDTO>{
    OrderMapper orderMapper = new OrderMapper();
    UserDtoMapper userDtoMapper = new UserDtoMapper();
    List<OrderDTO> orderDTOS = new ArrayList<>();
    @Override
    public BuyerDTO apply(Buyer buyer) {

        for(Order m : buyer.getOrders()){
            orderDTOS.add(orderMapper.apply(m));
        }
        return new BuyerDTO(
                buyer.getId(),
                userDtoMapper.apply(buyer.getUser()),
                orderDTOS
        );
}
}
