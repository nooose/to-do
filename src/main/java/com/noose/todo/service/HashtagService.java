package com.noose.todo.service;

import com.noose.todo.domain.note.entity.Hashtag;
import com.noose.todo.domain.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public Set<Hashtag> findAllByHashtagNames(Collection<String> hashtagNames) {
        return hashtagRepository.findAllByHashtagNameIn(hashtagNames);
    }

    @Transactional
    public void deleteUnusedHashtags(Collection<Hashtag> hashtags) {
        Set<Hashtag> removeHashTags = hashtags.stream()
                .filter(hashtag -> hashtag.getNoteHashtags().isEmpty())
                .collect(Collectors.toSet());

        hashtagRepository.deleteAllInBatch(removeHashTags);
    }
}
