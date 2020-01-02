package com.khrystyna.nerdysoft.controller;

import com.khrystyna.nerdysoft.dto.JwtResponse;
import com.khrystyna.nerdysoft.dto.forms.SignInForm;
import com.khrystyna.nerdysoft.dto.forms.SignUpForm;
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
    public JwtResponse signIn(@RequestBody SignInForm signInForm) {
        return authenticationService.attemptLogin(signInForm.getEmail(), signInForm.getPassword());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpForm signUpForm) {
        userService.save(User.builder()
                .username(signUpForm.getUsername())
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .build());
        return ResponseEntity.ok().build();
    }

}
