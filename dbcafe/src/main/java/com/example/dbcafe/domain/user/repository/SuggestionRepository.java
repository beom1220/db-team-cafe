package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Suggestion;
import com.example.dbcafe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
    List<Suggestion> findAllSuggestionByUser(User user);
}