package com.noose.todo.domain.repository;

import com.noose.todo.domain.note.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findAllByHashtagNameIn(Collection<String> hashtagNames);
}
