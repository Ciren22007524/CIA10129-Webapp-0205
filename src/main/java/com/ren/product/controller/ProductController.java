package com.ren.product.controller;

import com.Entity.ServiceRobot;
import com.ren.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productSvc;

    @GetMapping("/{pNo}")
    public Product getProduct(@PathVariable Integer pNo) {
        return productSvc.getOneProduct(pNo);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productSvc.getAll();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productSvc.addProduct(product);
    }

    @PutMapping("/products/{pNo}")
    public Product updateProduct(@PathVariable Integer pNo, @RequestBody Product product) {
        // Ensure the productNo in the path matches the productNo in the request body
        if (!pNo.equals(product.getpNo())) {
            throw new IllegalArgumentException("Path variable productNo must match the productNo in the request body");
        }
        return productSvc.updateProduct(product);
    }

    @DeleteMapping("/products/{pNo}")
    public void deleteProduct(@PathVariable Integer pNo) {
        productSvc.deleteProduct(pNo);
    }

}
