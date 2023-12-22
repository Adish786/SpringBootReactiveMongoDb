package com.reactive.controller;

import com.reactive.dto.ProductDto;
import com.reactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public Flux<ProductDto> products(){
    return productService.getProducts();
     }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProducts(@PathVariable String id ){
        return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductsBetweenRange(@RequestParam("min") double min,@RequestParam("max") double max ){
        return productService.getProductInRange(min,max);
    }

    @PostMapping
public Mono<ProductDto> saveProduct( @RequestBody Mono<ProductDto> productDtoMono){
        return productService.saveproduct(productDtoMono);
}

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct( @RequestBody Mono<ProductDto> productDtoMono,@PathVariable String id){
        return productService.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct( @PathVariable String id){
        return productService.deleteProduct(id);
    }


}
