package com.example.dbcafe.domain.user.dto;

import com.example.dbcafe.domain.user.domain.SuggestionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuggestionDto {
    private String title;

    private String content;

    private SuggestionCategory category;
}
