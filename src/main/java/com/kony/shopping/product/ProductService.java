package com.kony.shopping.product;

import com.kony.shopping.user.AddressRepository;
import com.kony.shopping.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductResMapper productResMapper = new ProductResMapper();
    private final SellerResMapper SellerResMapper = new SellerResMapper();
    private final StoreResMapper storeResMapper = new StoreResMapper();
    private final ProductDTOMapper productDTOMapper = new ProductDTOMapper();
    private final StoreDTOMapper storeDTOMapper = new StoreDTOMapper();
    private final SellerDTOMapper sellerDTOMapper = new SellerDTOMapper();

    List<ProductDTO> getProduct(Integer storeId) {
        var st = storeRepository.findById(storeId).get();
        return productRepository.findAllByStore(st).stream().map(productDTOMapper).collect(Collectors.toList());

    }

    Optional<ProductDTO> getProductById(int id) {
        return productRepository.findById(id).map(productDTOMapper);
    }

    List<StoreDTO> getStore() {
        return storeRepository.findAll().stream().map(
                storeDTOMapper
        ).toList();
    }

    Optional<StoreDTO> getStoreById(int id) {
        return storeRepository.findById(id).map(storeDTOMapper);
    }

    List<SellerDTO> getSeller() {
        return sellerRepository.findAll().stream().map(
                sellerDTOMapper
        ).toList();
    }

    Optional<SellerDTO> getSellerById(int id) {
        return sellerRepository.findById(id)
                .map(sellerDTOMapper);
    }

//    void addStore(int id, StoreDTO store) {
//        storeRepository.findById(id);
//        storeRepository.save(new Store(
//
//        ));
//    }

    void addStoreWithProduct(StoreRes storeRes, int sellerId) {
        sellerRepository.findById(sellerId).map(
                seller -> {
                    var mapper = new ProductResMapper();
                    var products = storeRes.products();
                    for (ProductRes p : products) {
                        productRepository.save(mapper.apply(p));
                    }
                    var store = storeResMapper.apply(storeRes);
                    store.setSeller(seller);

                    storeRepository.save(store);
                    return "";
                }
        ).orElseThrow();


    }

    void addProduct(ProductRes productRes, int storeId, int sellerId) {
        var product = productResMapper.apply(productRes);
        storeRepository.findById(storeId).map(
                m -> sellerRepository.findById(sellerId).map(
                        sell -> {
                            product.setStore(m);
                            productRepository.save(product);
                            return "";
                        }
                ).orElseThrow()
        ).orElseThrow();
    }

    void addSellerUserId(SellerRes sellerRes) {
        var seller = SellerResMapper.apply(sellerRes);

        userRepository.save(seller.getUser());
        sellerRepository.save(seller);
    }

    Boolean addSellerUserId(int userId) {
        var user = userRepository.findById(userId);

        return user.map(
                u -> {
                    var seller = new Seller();
                    seller.setUser(u);

                    sellerRepository.save(seller);
                    return true;
                }

        ).orElse(false);
    }


    void deleteProduct(ProductRes product) {
        productRepository.delete(productResMapper.apply(product));
    }

    void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    void deleteStore(StoreRes store) {
        storeRepository.delete(storeResMapper.apply(store));
    }

    void deleteStoreById(int id) {
        storeRepository.deleteById(id);
    }

    void deleteSeller(Seller seller) {
        sellerRepository.delete(seller);
    }

    void deleteSellerById(int id) {
        sellerRepository.deleteById(id);
    }

}
