package com.example.dbcafe.domain.user.service;

import com.example.dbcafe.domain.user.domain.Suggestion;
import com.example.dbcafe.domain.user.domain.User;
import com.example.dbcafe.domain.user.dto.SuggestionDto;
import com.example.dbcafe.domain.user.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;

    public List<Suggestion> findAllByUser(User user) {
        return suggestionRepository.findAllSuggentionByUser(user);
    }

    public void addSuggestion(SuggestionDto dto, User user) {
        Suggestion suggestion = new Suggestion(user, dto.getTitle(), dto.getContent(), dto.getCategory());
        suggestionRepository.save(suggestion);
    }
}
