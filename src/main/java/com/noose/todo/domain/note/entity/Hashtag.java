package com.noose.todo.domain.note.entity;

import com.noose.todo.domain.note.NoteHashtag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Getter
    private String hashtagName;

    @OneToMany(mappedBy = "hashtag")
    private List<NoteHashtag> noteHashtags;

    public Hashtag(String hashtagName) {
        if (hashtagName == null) {
            throw new IllegalArgumentException("null 또는 빈 문자열이 될 수 없습니다.");
        }

        this.hashtagName = hashtagName;
    }

    @Override
    public String toString() {
        return hashtagName;
    }
}
