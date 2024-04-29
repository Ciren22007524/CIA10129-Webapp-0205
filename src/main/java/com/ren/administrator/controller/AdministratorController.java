package com.ren.administrator.controller;

import com.Entity.Administrator;
import com.Entity.Product;
import com.ren.administrator.service.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/backend/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorServiceImpl administratorSvc;

    @GetMapping("/listOneAdministrator")
    public Administrator getAdministrator(@PathVariable Integer admNo) {
        return administratorSvc.getOneAdministrator(admNo);
    }

    @GetMapping("/listAllAdministrators")
    public List<Administrator> getAllAdministrators() {
        return administratorSvc.getAll();
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

    @DeleteMapping("/administrators/{admNo}")
    public void deleteAdministrator(@PathVariable Integer admNo) {
        administratorSvc.deleteAdministrator(admNo);
    }

    @ModelAttribute("AllProducts")
    protected List<Product> getAllAs(){
        return null;
    }

}
