package com.khrystyna.nerdysoft.service.interfaces;

import com.khrystyna.nerdysoft.dto.JwtResponse;

public interface AuthenticationService {
    JwtResponse attemptLogin(String username, String password);
}
