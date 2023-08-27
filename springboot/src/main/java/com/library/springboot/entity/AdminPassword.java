package com.library.springboot.entity;

import lombok.Data;

@Data
public class AdminPassword {
    int id;
    String oldPassword;
    String newPassword;
}
