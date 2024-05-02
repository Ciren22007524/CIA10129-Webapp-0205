package com.ren.administrator.controller;

import com.Entity.Administrator;
import com.ren.administrator.service.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/backend/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorServiceImpl administratorSvc;

    // 回首頁
    @GetMapping("/index")
    public String toIndex() {
        return "/backend/index";
    }

    // 查看管理員資料
    @GetMapping("/listOneAdministrator")
    public String getAdministrator(@PathVariable Integer admNo, ModelMap modelMap) {
        administratorSvc.getOneAdministrator(admNo);
        return "/backend/administrator/listOneAdministrator";
    }

    // 查看所有管理員資料
    @GetMapping("/listAllAdministrators")
    public String listAll() {
        return "/backend/administrator/listAllAdminstrators";
    }

    // 新增管理員
    @PostMapping("/addAdministrator")
    public Administrator addAdministrator(@RequestBody Administrator administrator) {
        return administratorSvc.addAdministrator(administrator);
    }

    // 更新管理員
    @PutMapping("/updateAdministrator")
    public Administrator updateAdministrator(@PathVariable Integer admNo, @RequestBody Administrator administrator) {
        // Ensure the productNo in the path matches the productNo in the request body
        if (!admNo.equals(administrator.getAdmNo())) {
            throw new IllegalArgumentException("Path variable productNo must match the productNo in the request body");
        }
        return administratorSvc.updateAdministrator(administrator);
    }

    // 刪除管理員
    @DeleteMapping("/administrators/{admNo}")
    public void deleteAdministrator(@PathVariable Integer admNo) {
        administratorSvc.deleteAdministrator(admNo);
    }

    /**
     * 將所有管理員資料渲染到前端頁面
     *
     * @return 所有管理員資料到訪問 /backend/administrator url的視圖
     */
    @ModelAttribute("AllProducts")
    protected List<Administrator> getAllAdministrators(){
        return administratorSvc.getAll();
    }

    @GetMapping("/selectAdministrator")
    public String toSelect() {
        return "/backend/administrator/selectAdministrator";
    }

}
