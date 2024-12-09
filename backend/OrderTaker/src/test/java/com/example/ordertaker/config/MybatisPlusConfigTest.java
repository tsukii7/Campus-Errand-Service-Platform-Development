package com.example.ordertaker.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MybatisPlusConfigTest {

    private MybatisPlusConfig mybatisPlusConfigUnderTest;

    @BeforeEach
    void setUp() {
        mybatisPlusConfigUnderTest = new MybatisPlusConfig();
    }

    @Test
    void testMybatisPlusInterceptor() {
        // Setup
        // Run the test
        final MybatisPlusInterceptor result = mybatisPlusConfigUnderTest.mybatisPlusInterceptor();

        // Verify the results
    }
}
