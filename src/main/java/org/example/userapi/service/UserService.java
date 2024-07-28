package org.example.userapi.service;

import org.example.userapi.domain.entity.User;
import org.example.userapi.model.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserRequest userRequest);

    List<User> getAll();

    Optional<User> getById(Long id);

    void deleteById(Long id);

    User updateUser(UserRequest userRequest, Long id);

    Integer count();

    void deleteAll();
}
