package com.noose.todo.domain.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Hashtag 일급 컬렉션 테스트")
class HashtagsTest {

    @DisplayName("본문 내용에서 해시태그를 추출할 수 있다.")
    @ParameterizedTest(name = "{0} --> {1}")
    @MethodSource
    void extractHashtagsTest(Body body, Set<String> hashtags) {
        Set<String> result = new Hashtags(body).getValues()
                .stream()
                .map(Hashtag::getHashtagName)
                .collect(Collectors.toSet());

        assertThat(result).isEqualTo(hashtags);
    }

    static Stream<Arguments> extractHashtagsTest() {
        return Stream.of(
                arguments("", Set.of()),
                arguments(" ", Set.of()),
                arguments("#", Set.of()),
                arguments("# ", Set.of()),
                arguments(" #", Set.of()),
                arguments(" # ", Set.of()),
                arguments("#안녕", Set.of("안녕")),
                arguments("#안녕_Hello", Set.of("안녕_Hello")),
                arguments("#안녕#하세요", Set.of("안녕", "하세요")),
                arguments("#안녕 #하세요", Set.of("안녕", "하세요")),
                arguments("#안녕 #하세요#안녕 #안녕", Set.of("안녕", "하세요")),
                arguments("테스트 #안녕 #하세요 테스트", Set.of("안녕", "하세요")),
                arguments("테스트 #good #test 테스트", Set.of("good", "test")),
                arguments("테스트 #good #test #A123 테스트", Set.of("good", "test", "A123"))
        );
    }
}
