package com.noose.todo.domain.repository;

import com.noose.todo.domain.note.entity.Hashtag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HashtagRepositoryTest {

    @Autowired
    HashtagRepository hashtagRepository;

    @DisplayName("해시태그 문자열로 찾는다.")
    @Test
    void findHashtagTest() {
        String hashtagNameA = "A";
        String hashtagNameB = "B";
        String hashtagNameC = "C";

        Hashtag hashtagA = new Hashtag(hashtagNameA);
        Hashtag hashtagB = new Hashtag(hashtagNameB);
        Hashtag hashtagC = new Hashtag(hashtagNameC);

        hashtagRepository.saveAll(List.of(hashtagA, hashtagB, hashtagC));

        Set<Hashtag> hashtags = hashtagRepository.findAllByHashtagNameIn(Set.of(hashtagNameA, hashtagNameC, hashtagNameB));

        assertThat(hashtags).contains(hashtagA, hashtagB, hashtagC);
    }
}
