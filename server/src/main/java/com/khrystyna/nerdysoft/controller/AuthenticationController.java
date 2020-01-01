package com.khrystyna.nerdysoft.controller;

import com.khrystyna.nerdysoft.dto.JwtResponse;
import com.khrystyna.nerdysoft.dto.SignInDto;
import com.khrystyna.nerdysoft.dto.SignUpDto;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.service.interfaces.AuthenticationService;
import com.khrystyna.nerdysoft.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-in")
    public JwtResponse signIn(@RequestBody SignInDto signInDto) {
        return authenticationService.attemptLogin(signInDto.getUsername(), signInDto.getPassword());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDto signUpDto) {
        userService.save(User.builder()
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build());
        return ResponseEntity.ok().build();
    }

}
