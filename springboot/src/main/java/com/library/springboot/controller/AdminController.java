package com.library.springboot.controller;

import com.library.springboot.common.Result;
import com.library.springboot.entity.Admin;
import com.library.springboot.entity.AdminPassword;
import com.library.springboot.entity.AdminRequest;
import com.library.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin  // 解決前端fetch data時出現‘has been blocked by CORS policy’
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @GetMapping("/list")
    public Result getAllAdmins() throws IOException {
        Object obj = adminService.getAllAdmins();
        return Result.success(obj);
    }

    @GetMapping("/listByCond")
    public Result getAdminsByCondition(AdminRequest adminRequest) throws IOException {
        Object obj = adminService.getAdminsByCondition(adminRequest);
        return Result.success(obj);
    }

    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return Result.success();
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public Result deleteAdmin(@PathVariable("id") Integer id) {
        adminService.deleteAdmin(id);
        return Result.success();
    }

    @PostMapping("/updateAdminPassword")
    public Result updateAdmin(@RequestBody AdminPassword adminPassword) {
        Boolean isUpdated = adminService.updateAdminPassword(adminPassword);
        if(isUpdated){
            return Result.success();
        }else{
            return Result.error("old password didn't match!");
        }
    }
}
