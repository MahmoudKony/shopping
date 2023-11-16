package com.kony.shopping.user;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserDtoMapper implements Function<User, UserDTO> {
    AddressDtoMapper mapper = new AddressDtoMapper();

    @Override
    public UserDTO apply(User user) {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for (Address ad : user.getAddress()) {
            addressDTOS.add(mapper.apply(ad));

        }
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getPhoneNumber(),
                user.getSocialMediaProfiles(),
                user.getDateOfBirth(),
                addressDTOS
        );
    }
}
