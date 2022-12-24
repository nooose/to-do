package com.noose.todo.domain.schedule;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR)
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
}
