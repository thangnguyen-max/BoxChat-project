package com.example.boxchat_java.repository;

import com.example.boxchat_java.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    User findByEmail(String email);

    List<User> findByEmailContainingIgnoreCaseOrNameContainingIgnoreCase( String name , String email );
}
