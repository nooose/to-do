package com.noose.todo.domain.note;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Body {
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#[\\w가-힣]+");
    private static final int MAX_LENGTH = 500;

    @Column
    private String contents;

    public Body(String contents) {
        validate(contents);

        this.contents = contents;
    }

    private void validate(String contents) {
        if (contents == null) {
            throw new IllegalArgumentException("본문 내용은 null 일 수 없습니다.");
        }

        if (contents.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("본문 내용 길이는" + MAX_LENGTH + "보다 클 수 없습니다.");
        }
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public List<String> parseHashtags() {
        Matcher matcher = HASHTAG_PATTERN.matcher(contents.strip());
        Set<String> result = new LinkedHashSet<>();

        while (matcher.find()) {
            String hashtag = matcher.group().replace("#", "");
            result.add(hashtag);
        }

        return List.copyOf(result);
    }
}
