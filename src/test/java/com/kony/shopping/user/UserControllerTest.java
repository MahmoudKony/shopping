package com.kony.shopping.user;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    //    @Autowired
//    UserRepository userRepository;

    //    @Autowired
//    private WebTestClient webTestClient;
//    @Container
//    static PostgreSQLContainer<?> postgres ;
    @RestartScope
    @ServiceConnection
    public static PostgreSQLContainer<?> getPostgres() {
        return new PostgreSQLContainer<>("postgres:16.0-alpine");
    }


    @BeforeEach
    void setUp() {
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


    }

    @Test
    void getUsers() {
        var users =
                testRestTemplate
                        .getForObject("/api/v1/users",
                                UserDTO[].class);

        for (UserDTO user : users) {
            System.out.println(user);
        }
        assertThat(users.length).isGreaterThan(0);
    }

    @Test
    void getAddressByUserId() {
        var addresses = testRestTemplate.getForObject("/api/v1/users/1/address",
                AddressDTO[].class);
        for (AddressDTO address : addresses) {
            System.out.println(address);
        }
        assertThat(addresses).isNotEmpty();
    }

    @Test
    void getAddressById() {
        var address = testRestTemplate.getForObject(
                "/api/v1//address/1",
                AddressDTO.class
        );
        System.out.println(address);
        assertThat(address).isNotNull();
    }

    @Test
    void setUser() {
        AddressRes addressRes = new AddressRes(
                "Khartopm",
                "Bahrey",
                "Mozedelfa",
                "1121",
                "092666623"
        );

        var user = new UserRes(
                "Man",
                "pass",
                "man@me.com",
                "Man",
                Gender.MALE,
                "man@Twiter.com",
                LocalDate.of(1996, 8, 5),
                List.of(addressRes)
        );
        var status = testRestTemplate.exchange("/api/v1/users", HttpMethod.POST,
                new HttpEntity<>(user, createHeaders()), Void.class);

        userRepository.findAll().forEach(System.out::println);

        assertThat(status.getStatusCode().value()).isEqualTo(HttpStatus.SC_CREATED);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    void addAddressByUserId() {
        AddressRes res = new AddressRes(
                "KNG",
                "EF",
                "23KR",
                "5152",
                "89566655"

        );

        var u = testRestTemplate.exchange("/api/v1/users/1/address",
                HttpMethod.POST, new HttpEntity<>(res, createHeaders()), Void.class);
        var user = userRepository.findById(1).get();
        addressRepository.findAllByUser(user).forEach(System.out::println);
        assertThat(u.getStatusCode().value()).isEqualTo(HttpStatus.SC_CREATED);
    }
//
//    @Test
//    void getUserById() {
//    }
//
//    @Test
//    void getAddressUser() {
//    }
//
//    @Test
//    void getAddressById() {
//    }
//
//    @Test
//    void putUser() {
//    }
//
//    @Test
//    void addAddress() {
//    }
//
    @Test
    void deleteAddressByIdAndUserId() throws Exception {
        int userId = 1;
        int addressId = 1;
        testRestTemplate.delete("/api/v1/users/1/address/1",userId,addressId);
        var p = addressRepository.existsById(1);
        assertThat(p).isFalse();
    }
//

//
    @Test
    void deleteUserById() {
        int userId =1;
        testRestTemplate.delete("/api/v1/users/1");
        var p = addressRepository.existsById(1);
        assertThat(p).isFalse();
    }
}