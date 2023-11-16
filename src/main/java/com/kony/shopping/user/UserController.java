package com.kony.shopping.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User")
class UserController {

    @Autowired
    UserService service;
    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> users(){
        return ResponseEntity.ok().body(
                service.users()
        );
    }
    @GetMapping("/users/{id}")
    ResponseEntity<User> getUserById(@PathVariable @Validated int id){
        var user = service.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/users/{userId}/address")
    ResponseEntity<List<AddressDTO>> getAddressByUserId(@PathVariable @Validated int userId){
        return ResponseEntity.ok(service.getAddressByUserId(userId));
    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    void setUser(@RequestBody @Validated UserRes user){
        service.setUser(user);
    }

//    @GetMapping("/address/{addressId}")
//    ResponseEntity<AddressDTO> getAddressById(@PathVariable @Validated int addressId){
//        return service.getAddressById(addressId).map(
//                ResponseEntity::ok
//        ).orElse(ResponseEntity.noContent().build());
//    }

    @PostMapping("/users/{userId}/address")
    @ResponseStatus(HttpStatus.CREATED)
    void addAddress(@PathVariable @Validated int userId,
                    @RequestBody AddressRes addressRes){
        service.addAddress(userId,addressRes);
    }
    @DeleteMapping("/users/{userId}/address/{addressId}")
    void deleteAddressByIdAndUserId(@PathVariable @Validated int userId,
                                    @PathVariable @Validated int addressId){
        service.deleteAddressByIdAndUserId(userId,addressId);
    }



    @DeleteMapping("/users/{id}")
    void deleteUserById(@PathVariable @Validated int id){
        service.deleteUserById(id);
    }

    


}
