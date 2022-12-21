package com.noose.todo.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class Hashtags {
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#[\\w가-힣]+");

    @Getter
    private Set<Hashtag> values = new LinkedHashSet<>();

    public Hashtags(Body body) {
        this.values = parseHashtags(body).stream()
                .map(Hashtag::new)
                .collect(Collectors.toSet());
    }

    private Set<String> parseHashtags(Body body) {
        Matcher matcher = HASHTAG_PATTERN.matcher(body.getContents().strip());
        Set<String> result = new HashSet<>();

        while (matcher.find()) {
            String hashtag = matcher.group().replace("#", "");
            result.add(hashtag);
        }

        return Set.copyOf(result);
    }

    public Set<String> hashtags() {
        return values.stream().map(Hashtag::getHashtagName).collect(Collectors.toSet());
    }
}
