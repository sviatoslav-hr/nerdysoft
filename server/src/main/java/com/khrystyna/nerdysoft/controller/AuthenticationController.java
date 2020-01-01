package com.khrystyna.nerdysoft.controller;

import com.khrystyna.nerdysoft.dto.SignUpDto;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDto signUpDto) {
        log.error(signUpDto.toString());
        userService.save(User.builder()
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build());
        return ResponseEntity.status(HttpStatus.OK).body("im good");
    }

}
