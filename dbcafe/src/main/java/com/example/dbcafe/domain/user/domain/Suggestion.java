package com.example.dbcafe.domain.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private SuggestionCategory category;

    private String answer;

    public Suggestion(User user, String title, String content, SuggestionCategory category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Suggestion(User user, String title, String content, SuggestionCategory category, String answer) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
        this.answer = answer;
    }
}
