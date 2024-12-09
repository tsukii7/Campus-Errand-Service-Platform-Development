package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ordertaker.entity.Blacklist;
import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.service.BlacklistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourierServiceImplTest {

    @Mock
    private BlacklistService mockBlacklistService;

    @InjectMocks
    private CourierServiceImpl courierServiceImplUnderTest;

    @Test
    void testSelectOne() {
        // Setup
        final Courier expectedResult = new Courier();
        expectedResult.setCourierName("courierName");
        expectedResult.setEmail("email");
        expectedResult.setPassword("password");
        expectedResult.setRating(0.0);
        expectedResult.setBlockTimes(0);

        // Run the test
        final Courier result = courierServiceImplUnderTest.selectOne("email", "password");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);

        // Run the test
        final boolean result = courierServiceImplUnderTest.save(courier);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testPage1() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> expectedResult = List.of(courier);

        // Run the test
        final List<Courier> result = courierServiceImplUnderTest.page(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPage2() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> expectedResult = List.of(courier);

        // Run the test
        final List<Courier> result = courierServiceImplUnderTest.page("courierName", 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageOrderByRatingAsc() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> expectedResult = List.of(courier);

        // Run the test
        final List<Courier> result = courierServiceImplUnderTest.pageOrderByRatingAsc(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageOrderByRatingDesc() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> expectedResult = List.of(courier);

        // Run the test
        final List<Courier> result = courierServiceImplUnderTest.pageOrderByRatingDesc(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageOrderByBlockTimesAsc() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> expectedResult = List.of(courier);

        // Run the test
        final List<Courier> result = courierServiceImplUnderTest.pageOrderByBlockTimesAsc(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageOrderByBlockTimesDesc() {
        // Setup
        final Courier courier = new Courier();
        courier.setCourierName("courierName");
        courier.setEmail("email");
        courier.setPassword("password");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> expectedResult = List.of(courier);

        // Run the test
        final List<Courier> result = courierServiceImplUnderTest.pageOrderByBlockTimesDesc(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBlock() {
        // Setup
        when(mockBlacklistService.save(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)))
                .thenReturn(false);

        // Run the test
        final boolean result = courierServiceImplUnderTest.block(0L);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testUnblock() {
        // Setup
        when(mockBlacklistService.remove(any(LambdaQueryWrapper.class))).thenReturn(false);

        // Run the test
        final boolean result = courierServiceImplUnderTest.unblock(0L);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testSetBlockTimes() {
        assertThat(courierServiceImplUnderTest.setBlockTimes(0L, 0)).isFalse();
    }

    @Test
    void testSetRating() {
        assertThat(courierServiceImplUnderTest.setRating(0L, 0.0)).isFalse();
    }

    @Test
    void testIsBlock() {
        // Setup
        when(mockBlacklistService.getBaseMapper()).thenReturn(null);

        // Run the test
        final boolean result = courierServiceImplUnderTest.isBlock(0L);

        // Verify the results
        assertThat(result).isFalse();
    }
}
