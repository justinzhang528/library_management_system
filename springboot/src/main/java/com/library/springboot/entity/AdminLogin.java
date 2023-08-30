package com.library.springboot.entity;

import lombok.Data;

@Data
public class AdminLogin {
    String name;
    String email;
    String phone;
    String token;
}
