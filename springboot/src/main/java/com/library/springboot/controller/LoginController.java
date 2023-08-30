package com.library.springboot.controller;

import com.library.springboot.common.Result;
import com.library.springboot.entity.Admin;
import com.library.springboot.entity.AdminLogin;
import com.library.springboot.entity.LoginInput;
import com.library.springboot.service.AdminService;
import com.library.springboot.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
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
        Admin admin = this.adminService.getAdminByNameAndPassword(loginInput);
        if(admin==null){
            return Result.error("用戶名或密碼錯誤！");
        }

        AdminLogin adminLogin = new AdminLogin();
        BeanUtils.copyProperties(admin,adminLogin);

        String token = TokenUtils.genToken(String.valueOf(admin.getId()),admin.getPassword());
        adminLogin.setToken(token);
        Result result = Result.success();
        result.setData(adminLogin);
        return result;
    }
}
