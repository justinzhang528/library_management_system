package com.library.springboot.service;

import com.library.springboot.entity.*;

import java.io.IOException;
import java.util.List;

public interface IAdminService {
    List<Admin> getAllAdmins() throws IOException;
    List<Admin> getAdminsByCondition(AdminRequest AdminRequest) throws IOException;

    void addAdmin(Admin admin);

    void deleteAdmin(Integer id);

    String getAdminPasswordById(Integer id);

    Boolean updateAdminPassword(AdminPassword adminPassword);

    Admin getAdminByNameAndPassword(LoginInput loginInput);

    Admin getAdminById(Integer integer);
}
