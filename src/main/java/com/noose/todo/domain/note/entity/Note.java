package com.noose.todo.domain.note.entity;

import com.noose.todo.domain.note.AuditingFields;
import com.noose.todo.domain.note.Body;
import com.noose.todo.domain.note.Title;
import com.noose.todo.domain.note.Todos;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DiscriminatorColumn
@DiscriminatorValue("N")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Note extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;
    @Embedded
    private Title title;
    @Embedded
    private Body body;
    @Embedded
    private Todos todos = new Todos();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "note",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private Set<NoteHashtag> noteHashtags = new LinkedHashSet<>();

    public Note(Long id, Title title, Body body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static Note of(String title, String body) {
        return new Note(null, new Title(title), new Body(body));
    }

    public boolean isEmptyBody() {
        return body.isEmpty();
    }

    public void update(String title, String body) {
        this.title = new Title(title);
        this.body = new Body(body);
    }

    public void syncHashtags(Collection<Hashtag> existingHashTags) {
        List<String> hashtagNamesInBody = hashtagNamesInBody();
        List<String> existingHashtagNames = existingHashTags.stream()
                .map(Hashtag::getHashtagName)
                .toList();

        hashtagNamesInBody.forEach(hashtagName -> {
            if (!existingHashtagNames.contains(hashtagName)) {
                existingHashTags.add(Hashtag.of(hashtagName));
            }
        });

        List<NoteHashtag> noteHashtags = existingHashTags.stream()
                .map(NoteHashtag::from)
                .toList();
        this.noteHashtags.addAll(noteHashtags);
        noteHashtags.forEach(noteHashtag -> noteHashtag.setNote(this));
    }

    public List<String> hashtagNamesInBody() {
        return body.parseHashtags();
    }

    public List<Hashtag> hashTags() {
        return noteHashtags.stream()
                .map(NoteHashtag::getHashtag)
                .toList();
    }

    public void clearHashtags() {
        noteHashtags.clear();
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setNote(this);
    }

    public void addTodos(Todos todos) {
        this.todos.addAll(todos);
        todos.setNote(this);
    }

    public int todoSize() {
        return todos.size();
    }
}
