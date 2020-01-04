package com.khrystyna.nerdysoft.controllers;

import com.khrystyna.nerdysoft.dto.JwtResponse;
import com.khrystyna.nerdysoft.dto.forms.SignInForm;
import com.khrystyna.nerdysoft.dto.forms.SignUpForm;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.services.interfaces.AuthenticationService;
import com.khrystyna.nerdysoft.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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
    
    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        List<String> list = new LinkedList<>();
        list.add("her");
        list.add("tobi");
        list.add("a");
        list.add("ne");
        list.add("deploy");
        return ResponseEntity.ok(list);
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
