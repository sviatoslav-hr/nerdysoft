package com.khrystyna.nerdysoft.service.implementation;

import com.khrystyna.nerdysoft.exceptions.InvalidUserDetailsException;
import com.khrystyna.nerdysoft.models.User;
import com.khrystyna.nerdysoft.repository.UserRepository;
import com.khrystyna.nerdysoft.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new InvalidUserDetailsException(String.format("Invalid User : [%s %s]",
                    user.getUsername(), user.getPassword()));
        }
        userRepository.save(user);
    }


}