package com.ren.product.controller;

import com.Entity.Product;
import com.ren.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/backend/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productSvc;

    @GetMapping("/listOneProduct")
    public Product getProduct(@PathVariable Integer pNo) {
        return productSvc.getOneProduct(pNo);
    }

//    @GetMapping("/listAllProduct")
//    public List<Product> getAllProducts() {
//        return productSvc.getAll();
//    }

    // 全部json到前端
    @GetMapping("/listAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productSvc.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productSvc.addProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@PathVariable Integer pNo, @RequestBody Product product) {
        // Ensure the productNo in the path matches the productNo in the request body
        if (!pNo.equals(product.getpNo())) {
            throw new IllegalArgumentException("Path variable productNo must match the productNo in the request body");
        }
        return productSvc.updateProduct(product);
    }

//    @DeleteMapping("/products/{pNo}")
//    public void deleteProduct(@PathVariable Integer pNo) {
//        productSvc.deleteProduct(pNo);
//    }

}
