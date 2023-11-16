package com.kony.shopping.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public record UserDTO(
        Integer userId,
        String name,
        String email,
        Gender gender,

        String phoneNumber,
        String socialMediaProfiles,
        LocalDate dateOfBirth,
        List<AddressDTO> addresses
) {


}
