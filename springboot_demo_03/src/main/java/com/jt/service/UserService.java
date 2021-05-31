package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {

    void insertUser(User user);


    List<User> getUser();
}
