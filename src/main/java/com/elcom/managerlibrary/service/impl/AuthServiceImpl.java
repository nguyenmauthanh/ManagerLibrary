package com.elcom.managerlibrary.service.impl;

import com.elcom.managerlibrary.auth.CustomUserDetails;
import com.elcom.managerlibrary.model.User;
import com.elcom.managerlibrary.repository.UserRepository;
import com.elcom.managerlibrary.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements UserDetailsService,AuthService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username : " + username);
        return new CustomUserDetails(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    @Override
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseGet(null);
        if (user == null)
            throw new UsernameNotFoundException("User not found with id : " + id);
        return new CustomUserDetails(user);
    }
}
