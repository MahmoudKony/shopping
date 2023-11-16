package com.kony.shopping.user;

import java.util.function.Function;

public record AddressRes(
        String state,

        String city,
        String street,
        String postCode,
        String contactPhoneNumber

){}
class AddressResMapper implements Function<AddressRes,Address>{
    @Override
    public Address apply(AddressRes addressRes) {
        var address = new Address();
        address.setState(addressRes.state());
        address.setCity(addressRes.city());
        address.setStreet(addressRes.street());
        address.setPostCode(addressRes.postCode());
        address.setContactPhoneNumber(addressRes.contactPhoneNumber());
        return address;
    }
}
