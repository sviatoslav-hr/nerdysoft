package com.khrystyna.nerdysoft.service.implementation;

import com.khrystyna.nerdysoft.exceptions.InvalidUserDetailsException;
import com.khrystyna.nerdysoft.exceptions.UserNotFoundException;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.UserRepository;
import com.khrystyna.nerdysoft.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new InvalidUserDetailsException(String.format("Invalid User : [%s %s]",
                    user.getEmail(), user.getPassword()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " was not found"));
    }
}
