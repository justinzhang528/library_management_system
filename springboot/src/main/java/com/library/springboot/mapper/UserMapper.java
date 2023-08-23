package com.library.springboot.mapper;

import com.library.springboot.entity.User;
import com.library.springboot.entity.UserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    List<User> getUsersByCondition(UserRequest userRequest);

    void addUser(User user);

    void deleteUser(Integer id);

    void updateUser(User user);
}
