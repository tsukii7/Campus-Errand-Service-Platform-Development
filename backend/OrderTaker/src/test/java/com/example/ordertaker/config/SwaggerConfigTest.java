package com.example.ordertaker.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import springfox.documentation.spring.web.plugins.Docket;

class SwaggerConfigTest {

    private SwaggerConfig swaggerConfigUnderTest;

    @BeforeEach
    void setUp() {
        swaggerConfigUnderTest = new SwaggerConfig();
    }

    @Test
    void testDocket() {
        // Setup
        final Environment environment = new MockEnvironment();

        // Run the test
        final Docket result = swaggerConfigUnderTest.docket(environment);

        // Verify the results
    }
}
