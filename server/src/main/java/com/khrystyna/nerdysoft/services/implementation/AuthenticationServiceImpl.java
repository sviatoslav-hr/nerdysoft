package com.khrystyna.nerdysoft.services.implementation;

import com.khrystyna.nerdysoft.dto.JwtResponse;
import com.khrystyna.nerdysoft.exceptions.UserNotFoundException;
import com.khrystyna.nerdysoft.repository.UserRepository;
import com.khrystyna.nerdysoft.configs.security.jwt.JwtProvider;
import com.khrystyna.nerdysoft.services.interfaces.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    public AuthenticationServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
                                     JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public JwtResponse attemptLogin(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException(
                    String.format("User [%s] does not exist", email));
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtProvider.generateJwtToken(authentication);
        return new JwtResponse(token);
    }
}