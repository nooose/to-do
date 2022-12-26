package com.noose.todo.domain.note.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class NoteHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_hashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    private Note note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    public NoteHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
        hashtag.addNoteHashtag(this);
    }

    public static List<NoteHashtag> of(Collection<Hashtag> hashtags) {
        return hashtags.stream()
                .map(NoteHashtag::new)
                .toList();
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
