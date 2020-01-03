package com.khrystyna.nerdysoft.services.interfaces;

import com.khrystyna.nerdysoft.dto.JwtResponse;
import com.khrystyna.nerdysoft.models.User;

public interface AuthenticationService {

    JwtResponse attemptLogin(String username, String password);

    User getAuthenticatedUser();
}
