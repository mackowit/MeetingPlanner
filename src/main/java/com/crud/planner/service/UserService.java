package com.crud.planner.service;

import com.crud.planner.domain.User;
import com.crud.planner.exception.UserNotFoundException;
import com.crud.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User saveUser(final User user) {

        return userRepository.save(user);
    }

    public Optional<User> getUserById(final Long id) {

        return userRepository.findById(id);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(final User userAfterChange) {
        if (userRepository.existsById(userAfterChange.getId())) {
            return userRepository.save(userAfterChange);
        } else {
            throw new UserNotFoundException();
        }
    }
}
