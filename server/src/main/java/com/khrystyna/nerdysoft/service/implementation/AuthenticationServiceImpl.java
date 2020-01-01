package com.khrystyna.nerdysoft.service.implementation;

import com.khrystyna.nerdysoft.dto.JwtResponse;
import com.khrystyna.nerdysoft.repository.UserRepository;
import com.khrystyna.nerdysoft.security.jwt.JwtProvider;
import com.khrystyna.nerdysoft.service.interfaces.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public JwtResponse attemptLogin(String username, String password) {
        if (!userRepository.existsByEmail(username)) {
            throw new UsernameNotFoundException(
                    String.format("User [%s] does not exist", username));
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtProvider.generateJwtToken(authentication);
        return new JwtResponse(token);
    }
}
