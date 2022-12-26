package com.noose.todo.domain.note.entity;

import com.noose.todo.domain.note.AuditingFields;
import com.noose.todo.domain.note.Body;
import com.noose.todo.domain.note.Title;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DiscriminatorColumn
@DiscriminatorValue("N")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Note extends AuditingFields {

    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#[\\w가-힣]+");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;
    @Embedded
    private Title title;
    @Embedded
    private Body body;

    @OneToMany(mappedBy = "note")
    private List<NoteHashtag> noteHashtags = new ArrayList<>();

    public Note(Long id, Title title, Body body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static Note of(String title, String body) {
        return new Note(null, new Title(title), new Body(body));
    }

    public List<Todo> todos() {
        return List.of();
    }

    public boolean isEmptyBody() {
        return body.isEmpty();
    }

    public void update(String title, String body) {
        this.title = new Title(title);
        this.body = new Body(body);
    }

    public Set<String> parseHashtags() {
        Matcher matcher = HASHTAG_PATTERN.matcher(body.getContents().strip());
        Set<String> result = new LinkedHashSet<>();

        while (matcher.find()) {
            String hashtag = matcher.group().replace("#", "");
            result.add(hashtag);
        }

        return Set.copyOf(result);
    }

    public void addNoteHashtags(Collection<NoteHashtag> noteHashtags) {
        this.noteHashtags.addAll(noteHashtags);
        noteHashtags.forEach(noteHashtag -> noteHashtag.setNote(this));
    }
}
