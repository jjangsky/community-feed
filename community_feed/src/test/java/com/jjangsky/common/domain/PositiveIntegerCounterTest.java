package com.jjangsky.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveIntegerCounterTest {

    // 카운트는 1씩 증가한다.
    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();

        //then
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        //then
        assertEquals(0, counter.getCount());
    }




}