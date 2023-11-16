package com.kony.shopping.product;

import com.kony.shopping.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RestartScope
class ProductControllerTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    TestRestTemplate restTemplate;
    String url = "/api/v1";

    @RestartScope
    @ServiceConnection
    public static PostgreSQLContainer<?> getPostgres() {
        return new PostgreSQLContainer<>("postgres:16.0-alpine");
    }

    @BeforeEach
    void setUp() {
        ProductRes p = new ProductRes(
                "PK",
                "this is one of test",
                null,
                5.0,
                2
        );
        StoreRes storeRes = new StoreRes(
                "KAKA",
                "this is test",
                List.of(p)

        );
        StoreResMapper m = new StoreResMapper();
        var st = m.apply(storeRes);
        Seller s = new Seller();
        Address address = new Address();
        address.setCity("KH");
        address.setState("NAM");
        address.setStreet("ML");
        address.setContactPhoneNumber("45286");
        address.setPostCode("1112");
        User user = new User();
        user.setEmail("hota@me.com");
        user.setName("Hota");
        user.setGender(Gender.MALE);
        user.setPhoneNumber("5999900");
        user.setPassword("hota");
        user.setDateOfBirth(LocalDate.now(Clock.systemDefaultZone()));
        user.setAddress(List.of(address));
        address.setUser(user);

        System.out.println("%%%  " + user);

        s.setUser(user);
        s.setStores(List.of(st));
        st.setSeller(s);
        sellerRepository.save(s);
        storeRepository.save(st);


    }

    @Test
    void getProduct() {
        int productId = 1;
//        var p =restTemplate.getForObject(url+"/products/store/1",ProductDTO[].class );
//        for (ProductDTO product : p) {
//            System.out.println(p);
//        }
//        assertThat(p).isNotEmpty();
//
//        RequestSpecification requestSpecification = RestAssured.given().contentType("application/json");
//
//        var o = requestSpecification.get("/api/v1/products/1");
//        var kk = o.getStatusCode();
//        var opp = HttpStatus.OK.value();
//
//        assertThat(o.getStatusCode()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    void getProductById() {
        ProductDTO productDTO = restTemplate.getForObject(url + "/products/1", ProductDTO.class);
        System.out.println(productDTO);
        assertThat(productDTO).isNotNull();

    }
//
    @Test
    void getStore() {
        StoreDTO storeDTO = restTemplate.getForObject(url + "/stores", StoreDTO.class);
        System.out.println(storeDTO);
        assertThat(storeDTO).isNotNull();
    }
//
    @Test
    void getStoreById() {
        var store =restTemplate.getForObject(url+"/stores/1",StoreDTO.class);
        System.out.println(store);
        assertThat(store).isNotNull();
    }
//
    @Test
    void getSeller() {
        var sellers=restTemplate.getForObject(url+"/sellers",SellerDTO[].class);
        for (SellerDTO seller : sellers) {
            System.out.println(seller);
        }
        assertThat(sellers).isNotEmpty();
    }
//
    @Test
    void testGetSellerById() {
        var seller= restTemplate.getForObject(url+"/sellers/1",SellerDTO.class);
        System.out.println(seller);
        assertThat(seller).isNotNull();
    }
//
    @Test
    void addSeller() {
        var address = new AddressRes(
                "JAN",
                "KJ",
                "123W",
                "1256",
                null
        );
        var user = new UserRes(
                "Jmal",
                "56953233",
                "jmal@me.com",
                "password",
                Gender.MALE,
                "jamel@faecbook.com",
                LocalDate.now(),
                List.of(address)

        );
        var sellerRes = new SellerRes(
                user
        );
        SellerResMapper mapperSeller = new SellerResMapper();
        var s = mapperSeller.apply(sellerRes);
        var r = restTemplate.exchange(url+"/sellers",
                HttpMethod.POST,new HttpEntity<>(sellerRes,httpHeaders()), Void.class);
        for (Seller seller : sellerRepository.findAll()) {
            System.out.println(seller.getSellerId());
        }
        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }

    private HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
//
    @Test
    void addSellerByUserId() {
        var address = new AddressRes(
                "JO",
                "skk",
                "123W",
                "1256",
                null
        );
        var user = new UserRes(
                "JK",
                "56953233",
                "JKl@me.com",
                "password",
                Gender.MALE,
                "jk@faecbook.com",
                LocalDate.now(),
                List.of(address)

        );
        UserResMapper u = new UserResMapper();

        userRepository.save(u.apply(user));
        var o =restTemplate.exchange(url+"/sellers/user/2",
                HttpMethod.POST,
                new HttpEntity<>(null,httpHeaders())
                ,HttpStatus.class);
        for (Seller seller : sellerRepository.findAll()) {
            System.out.println(seller);
        }
        System.out.println(o.getStatusCode().value());


    }

    @Test
    void addStoreWithProduct() {
        ProductRes p = new ProductRes(
                "PK",
                "this is one of test",
                null,
                5.0,
                2
        );
        StoreRes storeRes = new StoreRes(
                "MAF",
                "this is test",
                List.of(p)

        );

        ResponseEntity<Void> response = restTemplate.exchange(url + "/sellers/1/store",
                HttpMethod.POST, new HttpEntity<>(storeRes, httpHeaders()),
                Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }
//    @Test
//    void addProduct() {
//    }
//
//    @Test
//    void deleteProduct() {
//    }
//
//    @Test
//    void deleteProductById() {
//    }
//
//    @Test
//    void deleteStore() {
//    }
//
//    @Test
//    void deleteStoreById() {
//    }
//
//    @Test
//    void deleteSeller() {
//    }
//
//    @Test
//    void deleteSellerById() {
//    }
}