package com.example.ordertaker.service.impl;

import com.example.ordertaker.entity.Administer;
import com.example.ordertaker.mapper.AdministerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AdministerServiceImplTest {

    @Mock
    private AdministerMapper administerMapper;

    @InjectMocks
    private AdministerServiceImpl administerServiceImplUnderTest;


    @Test
    void testCheckAdministerExist() {
        // Setup
        final Administer expectedResult = new Administer(0L, "adminName", "password");

        // Run the test
        final Administer result = administerServiceImplUnderTest.checkAdministerExist("adminName", "password");

        // Verify the results
        assertThat(result).isEqualTo(null);
    }
}
