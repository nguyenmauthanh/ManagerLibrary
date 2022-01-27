package com.elcom.managerlibrary.service;

import com.elcom.managerlibrary.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(User user);

    void deleteById(Long id);
}
