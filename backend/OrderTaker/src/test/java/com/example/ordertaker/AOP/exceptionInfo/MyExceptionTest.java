package com.example.ordertaker.AOP.exceptionInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyExceptionTest {

    private MyException myExceptionUnderTest;

    @BeforeEach
    void setUp() {
        myExceptionUnderTest = new MyException("errorCode", "errorMsg", new Exception("message"));
    }

    @Test
    void testFillInStackTrace() {
        assertThat(myExceptionUnderTest.fillInStackTrace()).isNotEqualTo(new Exception("message"));
    }
}
