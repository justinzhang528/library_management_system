package com.library.springboot.entity;

import lombok.Data;

@Data
public class Admin {
    int id;
    String name;
    String password;
    String email;
    String phone;
}
