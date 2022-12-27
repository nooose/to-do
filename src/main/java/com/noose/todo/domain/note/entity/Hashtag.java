package com.noose.todo.domain.note.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    private String hashtagName;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "hashtag",
            orphanRemoval = true)
    private Set<NoteHashtag> noteHashtags = new LinkedHashSet<>();

    public Hashtag(String hashtagName) {
        if (hashtagName == null) {
            throw new IllegalArgumentException("null 또는 빈 문자열이 될 수 없습니다.");
        }

        this.hashtagName = hashtagName;
    }

    public static Hashtag of(String hashtagName) {
        return new Hashtag(hashtagName);
    }

    public void addNoteHashtag(NoteHashtag noteHashtag) {
        noteHashtags.add(noteHashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return id.equals(hashtag.id) && hashtagName.equals(hashtag.hashtagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hashtagName);
    }

    @Override
    public String toString() {
        return hashtagName;
    }
}
