package com.kony.shopping.user;



import java.util.function.Function;

public class UserResMapper implements Function<UserRes, User> {
    @Override
    public User apply(UserRes userRes) {
        AddressResMapper addressResMapper = new AddressResMapper();

         var user= new User();
         user.setName(userRes.name());
         user.setPhoneNumber(userRes.phoneNumber());
         user.setEmail(userRes.email());
         user.setPassword(userRes.password());
         user.setGender(userRes.gender());
         user.setSocialMediaProfiles(userRes.socialMediaProfiles());
         user.setDateOfBirth(userRes.dateOfBirth());
         user.setAddress(userRes.address().stream()
                 .map(addressResMapper).peek(u-> u.setUser(user)).toList());

        return user;
    }
}
