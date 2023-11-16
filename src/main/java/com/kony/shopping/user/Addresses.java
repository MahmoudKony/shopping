package com.kony.shopping.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Function;

record AddressDTO(

        Integer addressId,
        String state,

        String city,
        String street,
        String postCode,
        String contactPhoneNumber
        ) {

}
class AddressDtoMapper implements Function<Address,AddressDTO>{
    @Override
    public AddressDTO apply(Address address) {
        return new AddressDTO(
                address.getAddressId(),
                address.getCity(),
                address.getState(),
                address.getStreet(),
                address.getPostCode(),
                address.getContactPhoneNumber()
                );
    }
}