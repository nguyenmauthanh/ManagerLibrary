package com.elcom.managerlibrary.controller;

import com.elcom.managerlibrary.auth.CustomUserDetails;
import com.elcom.managerlibrary.auth.LoginRequest;
import com.elcom.managerlibrary.auth.LoginResponse;
import com.elcom.managerlibrary.auth.jwt.JwtTokenProvider;
import com.elcom.managerlibrary.validation.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class AuthenController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenController.class);

    @Autowired
    public AuthenController() {
    }

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        new UserValidation().validateLogin(loginRequest.getUsername(), loginRequest.getPassword());

        // Xác thực thông tin người dùng Request lên, nếu không xảy ra exception tức là thông tin hợp lệ
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        }catch(AuthenticationException ex) {
            LOGGER.error(ex.toString());
            throw new ValidationException("Sai username/password.");
        }

        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
}
