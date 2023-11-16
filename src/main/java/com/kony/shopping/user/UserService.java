package com.kony.shopping.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserDtoMapper userDtoMapper = new UserDtoMapper();
    private final UserResMapper userResMapper = new UserResMapper();
    private final AddressDtoMapper addressDtoMapper = new AddressDtoMapper();
    private final AddressResMapper addressResMapper = new AddressResMapper();


    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    List<UserDTO> users() {
        return userRepository.findAll().stream().map(userDtoMapper).toList();
    }

    Optional<User> getUserById(int id){
        return userRepository.findById(id);

    }
    List<AddressDTO> getAddressByUserId(int userId){
        var user = userRepository.findById(userId).get();
        return addressRepository.findAllByUser(user).stream().map(addressDtoMapper).collect(Collectors.toList());
    }

    Optional<AddressDTO> getAddressById(int addressId){
        return addressRepository.findById(addressId).map(addressDtoMapper);
    }

    void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    void deleteAddressByIdAndUserId(int userId,int addressId){
        userRepository.findById(userId).map(
                user -> {
                    addressRepository.deleteById(addressId);
                    return null;
                }
        );
    }
    void setUser(UserRes userRes){
        User user = userResMapper.apply(userRes);
//        user.setDateOfBirth(LocalDate.now());  // when use test
        userRepository.save(user);
    }

    void addAddress(int userId ,AddressRes addressRes){
        Address address = addressResMapper.apply(addressRes);
        userRepository.findById(userId).map(
                m->{
                    m.getAddress().add(address);
                    address.setUser(m);
                    addressRepository.save(address);
                    return null;
                }
        );
    }


}
