package com.ren.product.controller;

import com.Entity.Product;
import com.Entity.ProductCategory;
import com.ren.product.service.ProductServiceImpl;
import com.ren.productcategory.service.ProductCategoryServiceImpl;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/backend/product")
public class ProductBackEndController {

    @Autowired
    private ProductServiceImpl productSvc;

    @Autowired
    private ProductCategoryServiceImpl productCategorySvc;

    // 前往商品管理頁面
    @GetMapping("/selectProduct")
    public String toSelect() {
        return "/backend/product/selectProduct";
    }

    // 列出商品詳情
    @GetMapping("/listOneProduct")
    public String getProduct(@PathVariable Integer productNo, HttpSession session) {
        return "/backend/product/listOneProduct";
    }

    // 列出所有商品
    @GetMapping("/listAllProducts")
    public String getAllProducts() {
        return "/backend/product/listAllProducts";
    }

    // 前往增加商品頁面
    @GetMapping("/addProduct")
    public String toAddProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productCategoryList", productCategorySvc.getAll());
        return "/backend/product/addProduct";
    }

    // 處理新增資料
    @PostMapping("/addProduct/add")
    public String addProduct(@Valid Product product, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("errors", result.getAllErrors());
            return"/backend/product/addProduct";
        } else {
            System.out.println("新增成功");
        }
        productSvc.addProduct(product);
        return "redirect:/backend/product/listAllProducts";
    }

    // 前往更新商品頁面
    @GetMapping("/updateProduct")
    public String toUpdateProduct(Model model) {
        List<Product> list = productSvc.getAll();
        model.addAttribute("productList", list);
        model.addAttribute("productCategoryList", productCategorySvc.getAll());
        model.addAttribute("product", list.get(0));
        return "/backend/product/updateProduct";
    }

    // 處理修改資料
    @PostMapping("/updateProduct/update")
    public String updateProduct(@Valid Product product, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("errors", result.getAllErrors());
            return"/backend/product/updateProduct";
        } else {
            System.out.println("新增成功");
        }
        productSvc.addProduct(product);
        return "redirect:/backend/product/listAllProducts";
    }

    // 商品下架功能

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

    /**
     * 選取下拉式選單後即時更新商品資訊
     *
     * @param productNo 傳入主鍵搜尋
     * @return 返回到前端顯示
     */
    @PostMapping("/getProductDetails")
    @ResponseBody
    public ResponseEntity<Product> getProductDetails(@RequestParam Integer productNo) {
        // 根據商品編號查詢商品詳情
        Product product = productSvc.getOneProduct(productNo);
        // 返回查詢結果
        return ResponseEntity.ok().body(product);
    }

}
