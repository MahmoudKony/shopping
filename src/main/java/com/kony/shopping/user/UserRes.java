package com.kony.shopping.user;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.List;

public record UserRes(

        String name,

        String phoneNumber,

        // my add
        String email,
        String password,
        Gender gender,
        String socialMediaProfiles,
        LocalDate dateOfBirth,
        List<AddressRes> address
) {
}

