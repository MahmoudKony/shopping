package com.kony.shopping.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RestartScope
class UserRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0-alpine");


    @Autowired
    private UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    void connectionTest() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }


    @BeforeEach
    void adduserAndAddressToDatabase() {
        addressRepository.deleteAll();
        Address address = new Address.AddressBuilder().city("KH")
                .state("NAM")
                .street("ML")
                .contactPhoneNumber("45286")
                .postCode("1112")
                .build();
        User user = new User.UserBuilder()
                .email("hota@me.com")
                .name("Hota")
                .gender(Gender.MALE)
                .phoneNumber("5999900")
                .password("hota")
                .dateOfBirth(LocalDate.now(Clock.systemDefaultZone()))
                .address(List.of(address))
                .build();
        address.setUser(user);

        System.out.println("%%%  " + user);
        userRepository.save(user);

//        addressRepository.save(address);
    }

    @Test
    void checkIfAddressFount() {
        var ad = addressRepository.count();
        List<Address> users =
                addressRepository.findAll();
        for (Address u : users) {

            System.out.println("####@@@  " + u);
            System.out.println("#### ## ##" + u.getUser());

        }
        assertThat(ad).isGreaterThan(0);

    }

    @Test
    void addAddressWhenUserExists() {
        var m = userRepository.findById(1);
        m.map(
                user -> {

                    Address address = new Address.AddressBuilder().city("KH")
                            .state("NAM")
                            .street("ML")
                            .contactPhoneNumber("45286")
                            .postCode("1112")
                            .user(user)
                            .build();
                    addressRepository.save(address);
                    return 0;

                }
        );
        addressRepository.findAll().forEach(
                System.out::println
        );

    }

    @Test
    void getAllUser() {
        List<User> users =
                userRepository.findAll();
        for (User u : users) {
            System.out.println("#$$$$$##" + u);

        }
//        assertThat(users.isEmpty()).isFalse();

    }

    @Test
    void getUserByAddress() {
        Address address = addressRepository.findById(1).get();
        var user = userRepository.findByAddress(address);

        System.out.println("OOO ): "+user);


        assertThat(user).isNotNull();
    }

    @Test
    void getAddressByUser() {
        var u = userRepository.findById(1).get();
        List<Address> addresses = addressRepository.findAllByUser(u);
        var oo = userRepository.count();
        for (Address address : addresses) {
            System.out.println("DDDD "+oo+":) "+address);
        }

    }
}