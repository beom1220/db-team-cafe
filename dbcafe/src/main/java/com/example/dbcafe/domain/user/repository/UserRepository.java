package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Level;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(String id);

    List<User> findAllUserByLevel(Level level);
}