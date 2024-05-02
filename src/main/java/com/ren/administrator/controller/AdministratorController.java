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

    @GetMapping("/listOneAdministrator")
    public String getAdministrator(@PathVariable Integer admNo, ModelMap modelMap) {
        administratorSvc.getOneAdministrator(admNo);
        return "/backend/administrator/listOneAdministrator";
    }

    @GetMapping("/listAllAdministrators")
    public String listAll() {
        return "/backend/administrator/listAllAdminstrators";
    }

    @PostMapping("/addAdministrator")
    public Administrator addAdministrator(@RequestBody Administrator administrator) {
        return administratorSvc.addAdministrator(administrator);
    }

    @PutMapping("/updateAdministrator")
    public Administrator updateAdministrator(@PathVariable Integer admNo, @RequestBody Administrator administrator) {
        // Ensure the productNo in the path matches the productNo in the request body
        if (!admNo.equals(administrator.getAdmNo())) {
            throw new IllegalArgumentException("Path variable productNo must match the productNo in the request body");
        }
        return administratorSvc.updateAdministrator(administrator);
    }

//    @DeleteMapping("/administrators/{admNo}")
//    public void deleteAdministrator(@PathVariable Integer admNo) {
//        administratorSvc.deleteAdministrator(admNo);
//    }

    @ModelAttribute("AllProducts")
    protected List<Administrator> getAllAdministrators(){

        return administratorSvc.getAll();
    }

    @GetMapping("/")
    public String toHome() {
        return "/listAll";
    }

    @GetMapping("/backend")
    public String tobackendHome() {
        return "/backend/listAll";
    }

    @GetMapping("/index")
    public String toIndex() {
        return "/backend/index";
    }

    @GetMapping("/selectAdministrator")
    public String toSelect() {
        return "/backend/administrator/selectAdministrator";
    }

}
