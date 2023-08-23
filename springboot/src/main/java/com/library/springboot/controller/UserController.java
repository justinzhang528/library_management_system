package com.library.springboot.controller;

import com.library.springboot.common.Result;
import com.library.springboot.entity.User;
import com.library.springboot.entity.UserRequest;
import com.library.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin  // 解決前端fetch data時出現‘has been blocked by CORS policy’
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public Result getAllUsers() throws IOException {
        Object obj = userService.getAllUsers();
        return Result.success(obj);
    }

    @GetMapping("/listByCond")
    public Result getUsersByCondition(UserRequest userRequest) throws IOException {
        Object obj = userService.getUsersByCondition(userRequest);
        return Result.success(obj);
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success();
    }

    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }
}
