package com.example.t2207a_springboot.controller;

import com.example.t2207a_springboot.entity.Product;
import com.example.t2207a_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAll();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> search(String search){
        return productService.searchProduct(search);
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer minPrice,
                                @RequestParam(required = false) Integer maxPrice){
        return productService.filterProducts(name,minPrice,maxPrice);
    }
}
