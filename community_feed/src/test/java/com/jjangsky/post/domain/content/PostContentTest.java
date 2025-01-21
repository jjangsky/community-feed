package com.jjangsky.post.domain.content;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        //given
        String text = "this is a test";

        // when
        PostContent content = new PostContent(text);

        //then
        assertEquals(text, content.getContentText());

    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError(){
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삻, 슳"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord){
        // given
        String content = koreanWord.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError(){
        // given
        String content = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value){
        //when, then
        assertThrows(IllegalArgumentException.class,  () -> new PostContent(value));
    }

}