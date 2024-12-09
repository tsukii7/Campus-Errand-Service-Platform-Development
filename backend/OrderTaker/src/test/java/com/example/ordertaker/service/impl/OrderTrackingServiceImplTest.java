package com.example.ordertaker.service.impl;

import com.example.ordertaker.entity.OrderTracking;
import com.example.ordertaker.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTrackingServiceImplTest {

    private OrderTrackingServiceImpl orderTrackingServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        orderTrackingServiceImplUnderTest = new OrderTrackingServiceImpl();
        orderTrackingServiceImplUnderTest.orderService = mock(OrderService.class);
    }

    @Test
    void testSave() {
        // Setup
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(orderTrackingServiceImplUnderTest.orderService.updateOrderTrackingId(0L, 0L)).thenReturn(false);

        // Run the test
        final boolean result = orderTrackingServiceImplUnderTest.save(orderTracking);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testUpdatePlacedTime() {
        assertThat(orderTrackingServiceImplUnderTest.updatePlacedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isFalse();
    }

    @Test
    void testUpdateTransitedTime() {
        assertThat(orderTrackingServiceImplUnderTest.updateTransitedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isFalse();
    }

    @Test
    void testUpdateArrivedTime() {
        assertThat(orderTrackingServiceImplUnderTest.updateArrivedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isFalse();
    }

    @Test
    void testUpdateConfirmedTime() {
        assertThat(orderTrackingServiceImplUnderTest.updateConfirmedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isFalse();
    }

    @Test
    void testUpdateCancelledTime() {
        assertThat(orderTrackingServiceImplUnderTest.updateCancelledTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isFalse();
    }

    @Test
    void testGetById() {
        // Setup
        final OrderTracking expectedResult = new OrderTracking();
        expectedResult.setId(0L);
        expectedResult.setOrderId(0L);
        expectedResult.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        final OrderTracking result = orderTrackingServiceImplUnderTest.getById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testTransitting() {
        // Setup
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<OrderTracking> expectedResult = List.of(orderTracking);

        // Run the test
        final List<OrderTracking> result = orderTrackingServiceImplUnderTest.transitting();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUntransitted() {
        // Setup
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<OrderTracking> expectedResult = List.of(orderTracking);

        // Run the test
        final List<OrderTracking> result = orderTrackingServiceImplUnderTest.untransitted();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
