package com.noose.todo.domain.note;

import com.noose.todo.domain.note.entity.Hashtag;
import com.noose.todo.domain.note.entity.Note;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Note 도메인 테스트")
class NoteTest {

    @DisplayName("Body 에 해시태그 문자열이 포함되었을 때 해당 hashtag 가 저장된다.")
    @Test
    void autoSaveHashtag() {
        String hashtagA = "새벽2시";
        String hashtagB = "공부";
        String hashtagC = "테스트";
        Note newNote = Note.of("오늘은 월요일", String.format("오늘은 #%s 까지 #%s 한다. #%s", hashtagA, hashtagB, hashtagC));

        List<String> hashtagNames = newNote.hashtagNamesInBody();
        Set<Hashtag> hashtags = hashtagNames.stream()
                .map(Hashtag::new)
                .collect(Collectors.toSet());

        newNote.syncHashtags(hashtags);

        assertThat(newNote.getNoteHashtags()).hasSize(3);
    }
}
