package com.khrystyna.nerdysoft.services.interfaces;

import com.khrystyna.nerdysoft.dto.JwtResponse;

public interface AuthenticationService {
    JwtResponse attemptLogin(String username, String password);
}
