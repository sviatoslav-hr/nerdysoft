package com.khrystyna.nerdysoft.services.implementation;

import com.khrystyna.nerdysoft.exceptions.UserNotFoundException;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.UserRepository;
import com.khrystyna.nerdysoft.security.Principal;
import com.khrystyna.nerdysoft.services.interfaces.MongoUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements MongoUserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " was not found"));
        return new Principal(user.getEmail(), user.getPassword());
    }
}
