package com.library.springboot.service;

import com.library.springboot.entity.*;
import com.library.springboot.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AdminService implements IAdminService{

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<Admin> getAllAdmins() throws IOException {
        return this.adminMapper.getAllAdmins();
    }

    @Override
    public List<Admin> getAdminsByCondition(AdminRequest adminRequest) throws IOException {
        return this.adminMapper.getAdminsByCondition(adminRequest);
    }

    @Override
    public void addAdmin(Admin admin) {
        this.adminMapper.addAdmin(admin);
    }

    @Override
    public void deleteAdmin(Integer id) {
        this.adminMapper.deleteAdmin(id);
    }

    @Override
    public String getAdminPasswordById(Integer id) {
        return this.adminMapper.getAdminPasswordById(id);
    }

    @Override
    public Boolean updateAdminPassword(AdminPassword adminPassword) {
        String password = this.adminMapper.getAdminPasswordById(adminPassword.getId());
        if(password.equals(adminPassword.getOldPassword())){
            this.adminMapper.updateAdminPassword(adminPassword);
            return true;
        }
        return false;   //如果舊密碼跟資料庫裡面的不一樣的話，返回0
    }

    @Override
    public Admin getAdminByNameAndPassword(LoginInput loginInput) {
        return this.adminMapper.getAdminByNameAndPassword(loginInput);
    }

    @Override
    public Admin getAdminById(Integer id) {
        return this.adminMapper.getAdminById(id);
    }
}
