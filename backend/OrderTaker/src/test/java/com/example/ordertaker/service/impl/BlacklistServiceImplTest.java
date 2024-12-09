package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ordertaker.entity.Blacklist;
import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.mapper.BlacklistMapper;
import com.example.ordertaker.service.CourierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlacklistServiceImplTest {

    @Mock
    private CourierService mockCourierService;

    @Mock
    private BlacklistMapper blacklistMapper;

    @InjectMocks
    private BlacklistServiceImpl blacklistServiceImplUnderTest;

    @Test
    void testPage1() {
        // Setup
        final List<Blacklist> expectedResult = List.of(
                new Blacklist(7L, 2L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));

        // Run the test
        final List<Blacklist> result = blacklistServiceImplUnderTest.page(1L, 3L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPage2() {
        // Setup
        final List<Blacklist> expectedResult = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));

        // Configure CourierService.list(...).
        final Courier courier = new Courier();
        courier.setId(0L);
        courier.setCourierName("courierName");
        courier.setAvatarUrl("avatarUrl");
        courier.setEmail("email");
        courier.setPassword("password");
        final List<Courier> couriers = List.of(courier);
        when(mockCourierService.list(any(LambdaQueryWrapper.class))).thenReturn(couriers);

        // Run the test
        final List<Blacklist> result = blacklistServiceImplUnderTest.page("courierName", 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPage2_CourierServiceReturnsNoItems() {
        // Setup
        when(mockCourierService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Blacklist> result = blacklistServiceImplUnderTest.page("courierName", 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testPageOrderByCreatedTimeAsc() {
        // Setup
        final List<Blacklist> expectedResult = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));

        // Run the test
        final List<Blacklist> result = blacklistServiceImplUnderTest.pageOrderByCreatedTimeAsc(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageOrderByCreatedTimeDesc() {
        // Setup
        final List<Blacklist> expectedResult = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));

        // Run the test
        final List<Blacklist> result = blacklistServiceImplUnderTest.pageOrderByCreatedTimeDesc(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
