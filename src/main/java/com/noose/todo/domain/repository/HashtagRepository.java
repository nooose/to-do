package com.noose.todo.domain.repository;

import com.noose.todo.domain.note.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Set<Hashtag> findAllByHashtagNameIn(Collection<String> hashtagNames);
}
