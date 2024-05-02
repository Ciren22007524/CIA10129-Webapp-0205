package com.ren.product.controller;

import com.Entity.Product;
import com.ren.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 前往商品管理首頁
     */
    @GetMapping("/selectProduct")
    public String toSelect() {
        return "/backend/product/selectProduct";
    }
    /**
     * 前往商品管理首頁
     */
    @GetMapping("/listOneProduct")
    public String getProduct(@PathVariable Integer pNo, HttpSession session) {
        return "/backend/product/listOneProduct";
    }
    /**
     * 前往商品管理首頁
     */
    @GetMapping("/listAllProducts")
    public String getAllProducts() {
        return "/backend/product/listAllProducts";
    }
    /**
     * 前往商品管理首頁
     */
    @PostMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/backend/product/addProduct";
    }
    /**
     * 前往商品管理首頁
     */
    @GetMapping("/updateProduct")
    public String updateProduct(Model model,String productNo) {
        Product product = productSvc.getOneProduct(Integer.valueOf(productNo));
        model.addAttribute("product", product);
        return "/backend/product/updateProduct";
    }

//    @DeleteMapping("/products/{pNo}")
//    public void deleteProduct(@PathVariable Integer pNo) {
//        productSvc.deleteProduct(pNo);
//    }

    /**
     * 提供所有商品資料列表供視圖渲染使用。
     * 使用 @ModelAttribute 註解，確保在處理請求時可用於視圖中的 productList 屬性。
     * 該屬性是一個包含所有會員的列表，由 productSvc.findAll() 方法獲取。
     *
     * @return 包含所有會員的列表。
     */
    @ModelAttribute("productList")
    protected List<Product> getAllData() {
        List<Product> list =productSvc.getAll();
        return list;
    }

}
