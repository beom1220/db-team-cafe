package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(String id);
}