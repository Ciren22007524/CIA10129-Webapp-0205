package com.ren.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend/product")
public class ProductFrontEndController {

    // 前往商品瀏覽頁面
    @GetMapping("/selectProduct")
    public String toBrowse() {
        return "frontend/product/selectProduct";
    }

    // 加入購物車

    // 前往結帳畫面

    // 確認是否有無登入

    // 書籤的篩選功能

    // 複合查詢
}
