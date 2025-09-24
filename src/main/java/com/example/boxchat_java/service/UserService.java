package com.example.boxchat_java.service;

import com.example.boxchat_java.domain.User;
import com.example.boxchat_java.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }

    public List<User> findByKeyword(String keyword) {
        List<User> allUsers =userRepository.findByEmailContainingIgnoreCaseOrNameContainingIgnoreCase(keyword ,keyword);
        int limit = 5;
        return allUsers.subList(0, Math.min(limit, allUsers.size()));
    }

    public Optional<User> getUserById(long id){
        return this.userRepository.findById(id);
    }
}

