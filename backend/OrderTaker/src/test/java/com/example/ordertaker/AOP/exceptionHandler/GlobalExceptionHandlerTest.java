package com.example.ordertaker.AOP.exceptionHandler;

import com.example.ordertaker.AOP.exceptionInfo.MyException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.sql.SQLIntegrityConstraintViolationException;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandlerUnderTest;

    @BeforeEach
    void setUp() {
        globalExceptionHandlerUnderTest = new GlobalExceptionHandler();
    }

    @Test
    void testMyExceptionHandler() {
        // Setup
        final MyException e = new MyException("errorCode", "errorMsg", new Exception("message"));

        // Run the test
        final ExceptionResponse result = globalExceptionHandlerUnderTest.MyExceptionHandler(null, e);

        // Verify the results
    }

    @Test
    void testExceptionHandler1() {
        // Setup
        final NullPointerException e = new NullPointerException("s");

        // Run the test
        final ExceptionResponse result = globalExceptionHandlerUnderTest.exceptionHandler(null, e);

        // Verify the results
    }

    @Test
    void testExceptionHandler2() {
        // Setup
        final TooManyResultsException e = new TooManyResultsException("message", new Exception("message"));

        // Run the test
        final ExceptionResponse result = globalExceptionHandlerUnderTest.exceptionHandler(null, e);

        // Verify the results
    }

    @Test
    void testExceptionHandler3() {
        // Setup
        final HttpMessageNotReadableException e = new HttpMessageNotReadableException("msg");

        // Run the test
        final ExceptionResponse result = globalExceptionHandlerUnderTest.exceptionHandler(null, e);

        // Verify the results
    }

    @Test
    void testExceptionHandler4() {
        // Setup
        final SQLIntegrityConstraintViolationException e = new SQLIntegrityConstraintViolationException("reason",
                "SQLState", 0, new Exception("message"));

        // Run the test
        final ExceptionResponse result = globalExceptionHandlerUnderTest.exceptionHandler(null, e);

        // Verify the results
    }
}
