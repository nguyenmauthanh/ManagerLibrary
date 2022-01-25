package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.model.User;
import com.elcom.managerlibrary.repository.UserCustomizeRepository;
import com.elcom.managerlibrary.repository.UserRepository;
import com.elcom.managerlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCustomizeRepository userCustomizeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserCustomizeRepository userCustomizeRepository) {
        this.userRepository = userRepository;
        this.userCustomizeRepository = userCustomizeRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userCustomizeRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
}
