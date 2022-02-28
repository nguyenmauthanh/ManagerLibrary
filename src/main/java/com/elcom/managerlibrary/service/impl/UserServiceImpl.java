package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.model.User;
import com.elcom.managerlibrary.repository.UserRepository;
import com.elcom.managerlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository  ) {
        this.userRepository = userRepository;

    }

    @Override
//    @Cacheable(value = "user")
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
//    @Cacheable(value = "user", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
//    @CacheEvict(value = "user", key = "#id")
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
