package com.example.ordertaker.AOP.exceptionHandler;

import com.example.ordertaker.AOP.exceptionInfo.BaseErrorInfoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExceptionResponseTest {

    @Mock
    private BaseErrorInfoInterface mockErrorInfo;

    private ExceptionResponse exceptionResponseUnderTest;

    @BeforeEach
    void setUp() {
        exceptionResponseUnderTest = new ExceptionResponse(mockErrorInfo);
    }

    @Test
    void testSuccess1() {
        // Run the test
        final ExceptionResponse result = ExceptionResponse.success();
        assertThat(result.getCode()).isEqualTo("2000");
        assertThat(result.getMessage()).isEqualTo("成功!");
        assertThat(result.getResult()).isEqualTo(null);
        assertThat(result.toString()).isEqualTo("{\"code\":\"2000\",\"message\":\"成功!\"}");
    }

    @Test
    void testSuccess2() {
        // Run the test
        final ExceptionResponse result = ExceptionResponse.success("data");
        assertThat(result.getCode()).isEqualTo("2000");
        assertThat(result.getMessage()).isEqualTo("成功!");
        assertThat(result.getResult()).isEqualTo("data");
        assertThat(result.toString()).isEqualTo("{\"code\":\"2000\",\"message\":\"成功!\",\"result\":\"data\"}");
    }

    @Test
    void testError1() {
        // Setup
        final BaseErrorInfoInterface errorInfo = null;

        // Run the test
        final ExceptionResponse result = ExceptionResponse.error("");
        assertThat(result.getCode()).isEqualTo("-1");
        assertThat(result.getMessage()).isEqualTo("");
        assertThat(result.getResult()).isEqualTo(null);
        assertThat(result.toString()).isEqualTo("{\"code\":\"-1\",\"message\":\"\"}");
    }

    @Test
    void testError2() {
        // Run the test
        final ExceptionResponse result = ExceptionResponse.error("code", "message");
        assertThat(result.getCode()).isEqualTo("code");
        assertThat(result.getMessage()).isEqualTo("message");
        assertThat(result.getResult()).isEqualTo(null);
        assertThat(result.toString()).isEqualTo("{\"code\":\"code\",\"message\":\"message\"}");
    }

    @Test
    void testError3() {
        // Run the test
        final ExceptionResponse result = ExceptionResponse.error("message");
        assertThat(result.getCode()).isEqualTo("-1");
        assertThat(result.getMessage()).isEqualTo("message");
        assertThat(result.getResult()).isEqualTo(null);
        assertThat(result.toString()).isEqualTo("{\"code\":\"-1\",\"message\":\"message\"}");
    }

    @Test
    void testToString() {
        assertThat(exceptionResponseUnderTest.toString()).isEqualTo("{}");
    }
}
