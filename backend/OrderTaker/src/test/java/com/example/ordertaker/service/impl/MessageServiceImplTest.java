package com.example.ordertaker.service.impl;

import com.example.ordertaker.entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageServiceImplTest {

    private MessageServiceImpl messageServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        messageServiceImplUnderTest = new MessageServiceImpl();
    }

    @Test
    void testSave() {
        // Setup
        final Message message = new Message();
        message.setClientId(0L);
        message.setCourierId(0L);
        message.setContent("content");
        message.setSenderId(0L);
        message.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        final boolean result = messageServiceImplUnderTest.save(message);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testPage() {
        // Setup
        final Message message = new Message();
        message.setClientId(0L);
        message.setCourierId(0L);
        message.setContent("content");
        message.setSenderId(0L);
        message.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Message> expectedResult = List.of(message);

        // Run the test
        final List<Message> result = messageServiceImplUnderTest.page(0L, 0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCount() {
        assertThat(messageServiceImplUnderTest.count(0L, 0L)).isEqualTo(0L);
    }
}
