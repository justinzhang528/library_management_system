package com.library.springboot.controller;

import com.library.springboot.common.Result;
import com.library.springboot.entity.Admin;
import com.library.springboot.entity.AdminLogin;
import com.library.springboot.entity.LoginInput;
import com.library.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin  // 解決前端fetch data時出現‘has been blocked by CORS policy’
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result Login(@RequestBody LoginInput loginInput){
        AdminLogin adminLogin = this.adminService.getAdminByNameAndPassword(loginInput);
        if(adminLogin==null){
            return Result.error("用戶名或密碼錯誤！");
        }
        Result result = Result.success();
        result.setData(adminLogin);
        return result;
    }
}
