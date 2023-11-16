package com.kony.shopping.product;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product" )
@RequestMapping("/api/v1")

public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/store/{storeId}")
    List<ProductDTO> getProduct(@PathVariable @Validated Integer storeId){
        return productService.getProduct(storeId);
    }
    @GetMapping("products/{productId}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable @Validated int productId){
        return productService.getProductById(productId).map(
                ResponseEntity::ok
        ).orElse(ResponseEntity.noContent().build());
    }
    @GetMapping("/stores")
    List<StoreDTO> getStore(){
        return productService.getStore();
    }
    @GetMapping("/stores/{storeId}")
    ResponseEntity<StoreDTO> getStoreById( @PathVariable @Validated int storeId){
        return productService.getStoreById(storeId).map(
                ResponseEntity::ok
        ).orElse(ResponseEntity.noContent().build());
    }
    @GetMapping("/sellers")
    List<SellerDTO> getSeller(){
        return productService.getSeller();
    }
    @GetMapping("/sellers/{id}")
    ResponseEntity<SellerDTO> getSellerById(@PathVariable @Validated int id){
        return productService.getSellerById(id).map(
                ResponseEntity::ok
        ).orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/sellers")
    @ResponseStatus(HttpStatus.CREATED)
    void addSeller(@RequestBody SellerRes seller){
        productService.addSellerUserId(seller);
    }
    @PostMapping("/sellers/user/{userId}")
    ResponseEntity<HttpStatus> addSellerByUserId(@PathVariable @Validated int userId){
         if (!productService.addSellerUserId(userId)) {
             return ResponseEntity.notFound().build();
        } else {
             return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
//    @PutMapping("/stores")
//    void addStore(@RequestBody Store store){
//        productService.addStore(store,id);
//    }
    @PostMapping("/sellers/{sellerId}/store")
    @ResponseStatus(HttpStatus.CREATED)
    void addStoreWithProduct(@RequestBody StoreRes store,
                             @PathVariable @Validated int sellerId){
        productService.addStoreWithProduct(store,sellerId);
    }
    @PostMapping("/products/seller/{sellerId}/store/{storeId}")
    @ResponseStatus(HttpStatus.CREATED)
    void addProduct(@RequestBody ProductRes productRes,
                    @PathVariable @Validated int storeId,
                    @PathVariable @Validated int sellerId){
        productService.addProduct(productRes,storeId,sellerId);
    }



    @DeleteMapping("/products")
    void deleteProduct(@RequestBody  ProductRes product){
        productService.deleteProduct(product);
    }
    @DeleteMapping("/products/{productId}")
    void deleteProductById(@PathVariable @Validated int productId){
        productService.deleteProductById(productId);
    }
    @DeleteMapping("/stores")
    void deleteStore(@RequestBody StoreRes store){
        productService.deleteStore(store);
    }
    @DeleteMapping("/stores/{storeId}")
    void deleteStoreById(@PathVariable @Validated int storeId){
        productService.deleteStoreById(storeId);
    }
    @DeleteMapping("/sellers")
    void deleteSeller(Seller seller){
        productService.deleteSeller(seller);
    }
    @DeleteMapping("/sellers/{sellerId}")
    void deleteSellerById(int sellerId){
        productService.deleteSellerById(sellerId);
    }


}
