package com.springboot.mybus.DAO;

import com.springboot.mybus.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> loginCheck(User user);
}
