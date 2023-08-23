package com.library.springboot.entity;

import lombok.Data;

@Data
public class User {
    int id;
    String name;
    int age;
    String address;
    String phone;
    int sex;
}