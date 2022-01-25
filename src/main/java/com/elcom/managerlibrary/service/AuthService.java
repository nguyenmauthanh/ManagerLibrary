package com.elcom.managerlibrary.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails loadUserById(Long theId);
    UserDetails loadUserByUsername(String username);
}
