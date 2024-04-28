package com.ren.admauthority.controller;

import com.Entity.AdmAuthority;
import com.ren.admauthority.service.AdmAuthorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/backend/admAuthority")
public class AdmAuthorityController {

    @Autowired
    private AdmAuthorityServiceImpl admAuthoritySvc;

    @GetMapping("/listOneAdmAuthority")
    public AdmAuthority getAdmAuthority(@PathVariable Integer titleNo) {
        return admAuthoritySvc.getOneAdmAuthority(titleNo);
    }

    @GetMapping("/listAllAdmAuthority")
    public List<AdmAuthority> getAllAdmAuthorities() {
        return admAuthoritySvc.getAll();
    }

    @PostMapping("/addAdmAuthority")
    public AdmAuthority addAdmAuthority(@RequestBody AdmAuthority admAuthority) {
        return admAuthoritySvc.addAdmAuthority(admAuthority);
    }

    @PutMapping("/updateAdmAuthority")
    public AdmAuthority updateAdmAuthority(@PathVariable Integer titleNo, @RequestBody AdmAuthority admAuthority) {
        // Ensure the productNo in the path matches the productNo in the request body
        if (!titleNo.equals(admAuthority.getTitle().getTitleNo())) {
            throw new IllegalArgumentException("Path variable productNo must match the productNo in the request body");
        }
        return admAuthoritySvc.updateAdmAuthority(admAuthority);
    }

    @DeleteMapping("/titles/{titleNo}")
    public void deleteAdmAuthority(@PathVariable Integer titleNo) {
        admAuthoritySvc.deleteAdmAuthority(titleNo);
    }
    
}
