package com.ren.product.controller;

import com.Entity.Product;
import com.ren.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/backend/product")
public class ProductBackEndController {

    @Autowired
    private ProductServiceImpl productSvc;

    @GetMapping("/selectProduct")
    public String showSelect() {
        return "/backend/product/selectProduct";
    }

    @GetMapping("/listOneProduct")
    public String getProduct(@PathVariable Integer pNo, HttpSession session) {
        return "/backend/product/listOneProduct";
    }

    @GetMapping("/listAllProducts")
    public String getAllProducts(ModelMap modelMap) {
        return "/backend/product/listAllProducts";
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productSvc.addProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@PathVariable Integer productNo, @RequestBody Product product) {
        // Ensure the productNo in the path matches the productNo in the request body
        if (!productNo.equals(product.getProductNo())) {
            throw new IllegalArgumentException("Path variable productNo must match the productNo in the request body");
        }
        return productSvc.updateProduct(product);
    }

//    @DeleteMapping("/products/{pNo}")
//    public void deleteProduct(@PathVariable Integer pNo) {
//        productSvc.deleteProduct(pNo);
//    }

    @ModelAttribute("productList")
    protected List<Product> getAllData() {
        List<Product> list =productSvc.getAll();
        return list;
    }

}
