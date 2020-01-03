package com.khrystyna.nerdysoft.services.interfaces;

import com.khrystyna.nerdysoft.models.User;

public interface UserService {

    void save(User user);

    User findByEmail(String email);

    User findById(String email);
}
