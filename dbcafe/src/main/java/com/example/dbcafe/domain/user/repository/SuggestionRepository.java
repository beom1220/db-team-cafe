package com.example.dbcafe.domain.user.repository;

import com.example.dbcafe.domain.user.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
}