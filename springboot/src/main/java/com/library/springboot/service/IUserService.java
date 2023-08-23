package com.library.springboot.service;

import com.library.springboot.entity.User;
import com.library.springboot.entity.UserRequest;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers() throws IOException;
    List<User> getUsersByCondition(UserRequest userRequest) throws IOException;

    void addUser(User user);

    void deleteUser(Integer id);

    void updateUser(User user);
}
