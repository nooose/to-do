package com.noose.todo.domain.schedule;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@DiscriminatorColumn @Inheritance(strategy = InheritanceType.JOINED)
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

    public boolean isEmptyBody() {
        return body.isEmpty();
    }

    public void updateTitle(String title) {
        this.title = new Title(title);
    }

    public void updateBody(String body) {
        this.body = new Body(body);
    }
}
