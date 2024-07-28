package org.example.userapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.userapi.domain.entity.User;
import org.example.userapi.domain.repository.UserRepository;
import org.example.userapi.exception.UserNotFoundException;
import org.example.userapi.model.UserRequest;
import org.example.userapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
        return userRepository.save(User.builder()
                .username(userRequest.username())
                .age(userRequest.age())
                .build());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(UserRequest userRequest, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        user.setUsername(userRequest.username());
        user.setAge(userRequest.age());
        return userRepository.update(user);
    }

    @Override
    public Integer count() {
        return userRepository.count();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
