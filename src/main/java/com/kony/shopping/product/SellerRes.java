package com.kony.shopping.product;

import com.kony.shopping.user.UserRes;
import com.kony.shopping.user.UserResMapper;

import java.util.function.Function;

record SellerRes(
        UserRes user
) {
}
class SellerResMapper implements Function<SellerRes,Seller> {
    @Override
    public Seller apply(SellerRes sellerRes) {
        var mapperUser = new UserResMapper();
        var seller = new Seller();

        seller.setUser(mapperUser.apply(sellerRes.user()));
        return seller;
    }
}
