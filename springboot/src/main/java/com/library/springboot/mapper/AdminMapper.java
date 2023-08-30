package com.library.springboot.mapper;

import com.library.springboot.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Admin> getAllAdmins();

    List<Admin> getAdminsByCondition(AdminRequest adminRequest);

    void addAdmin(Admin admin);

    void deleteAdmin(Integer id);

    void updateAdminPassword(AdminPassword adminPassword);

    String getAdminPasswordById(Integer id);

    Admin getAdminByNameAndPassword(LoginInput loginInput);

    Admin getAdminById(Integer id);
}
