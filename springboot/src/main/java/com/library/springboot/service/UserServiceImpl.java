package com.library.springboot.service;

import com.library.springboot.entity.User;
import com.library.springboot.entity.UserRequest;
import com.library.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    @Override
    public List<User> getUsersByCondition(UserRequest userRequest){
        return userMapper.getUsersByCondition(userRequest);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

}
