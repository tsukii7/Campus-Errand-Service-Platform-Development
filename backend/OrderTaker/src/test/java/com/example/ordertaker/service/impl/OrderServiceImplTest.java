package com.example.ordertaker.service.impl;

import com.example.ordertaker.constant.CurrentStatusEnum;
import com.example.ordertaker.constant.ServiceTypeEnum;
import com.example.ordertaker.entity.Order;
import com.example.ordertaker.entity.OrderTracking;
import com.example.ordertaker.mapper.OrderMapper;
import com.example.ordertaker.service.OrderTrackingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderTrackingService mockOrderTrackingService;
    @Mock
    private OrderMapper mockOrderMapper;

    @InjectMocks
    private OrderServiceImpl orderServiceImplUnderTest;

    @Test
    void testSave() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        final boolean result = orderServiceImplUnderTest.save(order);

        // Verify the results
        assertThat(result).isFalse();

        // Confirm OrderTrackingService.save(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        verify(mockOrderTrackingService).save(orderTracking);
    }

    @Test
    void testPageByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageByCourierId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageByCourierId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageTakeoutByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageTakeoutByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageExpressByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageExpressByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageFlashByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageFlashByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUnacceptedByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.unacceptedByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUnacceptedAll() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.unacceptedAll(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageUnconfirmedByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageUnconfirmedByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageConfirmedByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageConfirmedByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageCancelledByClientId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageCancelledByClientId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateStatus() {
        // Setup
        // Configure OrderTrackingService.getById(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockOrderTrackingService.getById(0L)).thenReturn(orderTracking);

        // Configure OrderTrackingService.updateById(...).
        final OrderTracking entity = new OrderTracking();
        entity.setId(0L);
        entity.setOrderId(0L);
        entity.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockOrderTrackingService.updateById(entity)).thenReturn(false);

        // Run the test
        final Date result = orderServiceImplUnderTest.updateStatus(0L, CurrentStatusEnum.PLACED, 0L);

        // Verify the results
        assertThat(result).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testUpdateStatus_OrderTrackingServiceGetByIdReturnsNull() {
        // Setup
        when(mockOrderTrackingService.getById(0L)).thenReturn(null);

        // Configure OrderTrackingService.updateById(...).
        final OrderTracking entity = new OrderTracking();
        entity.setId(0L);
        entity.setOrderId(0L);
        entity.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockOrderTrackingService.updateById(entity)).thenReturn(false);

        // Run the test
        final Date result = orderServiceImplUnderTest.updateStatus(0L, CurrentStatusEnum.PLACED, 0L);

        // Verify the results
        assertThat(result).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testUpdateReviewId() {
        assertThat(orderServiceImplUnderTest.updateReviewId(0L, 0L)).isFalse();
    }

    @Test
    void testRemoveReviewId() {
        assertThat(orderServiceImplUnderTest.removeReviewId(0L)).isFalse();
    }

    @Test
    void testUpdatePaymentId() {
        assertThat(orderServiceImplUnderTest.updatePaymentId(0L, 0L)).isFalse();
    }

    @Test
    void testUpdateOrderTrackingId() {
        assertThat(orderServiceImplUnderTest.updateOrderTrackingId(0L, 0L)).isFalse();
    }

    @Test
    void testCountByClientId() {
        assertThat(orderServiceImplUnderTest.countByClientId(0L)).isEqualTo(0L);
    }

    @Test
    void testCountByCourierId() {
        assertThat(orderServiceImplUnderTest.countByCourierId(0L)).isEqualTo(0L);
    }

    @Test
    void testCountTakeoutByClientId() {
        assertThat(orderServiceImplUnderTest.countTakeoutByClientId(0L)).isEqualTo(0L);
    }

    @Test
    void testCountExpressByClientId() {
        assertThat(orderServiceImplUnderTest.countExpressByClientId(0L)).isEqualTo(0L);
    }

    @Test
    void testCountFlashByClientId() {
        assertThat(orderServiceImplUnderTest.countFlashByClientId(0L)).isEqualTo(0L);
    }

    @Test
    void testCountUnconfirmedByClientId() {
        assertThat(orderServiceImplUnderTest.countUnconfirmedByClientId(0L)).isEqualTo(0L);
    }

    @Test
    void testPageUntransittedByCourierId() {
        // Setup
        final Order order = new Order();
        order.setId(1L);
        order.setClientId(0L);
        order.setCourierId(1L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(1L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Configure OrderTrackingService.untransitted(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(1L);
        orderTracking.setOrderId(1L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<OrderTracking> orderTrackings = List.of(orderTracking);
        when(mockOrderTrackingService.untransitted()).thenReturn(orderTrackings);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageUntransittedByCourierId(1L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageUntransittedByCourierId_OrderTrackingServiceReturnsNoItems() {
        // Setup
        when(mockOrderTrackingService.untransitted()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageUntransittedByCourierId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testCountUntransittedByCourierId() {
        // Setup
        // Configure OrderTrackingService.untransitted(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<OrderTracking> orderTrackings = List.of(orderTracking);
        when(mockOrderTrackingService.untransitted()).thenReturn(orderTrackings);

        // Run the test
        final long result = orderServiceImplUnderTest.countUntransittedByCourierId(0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCountUntransittedByCourierId_OrderTrackingServiceReturnsNoItems() {
        // Setup
        when(mockOrderTrackingService.untransitted()).thenReturn(Collections.emptyList());

        // Run the test
        final long result = orderServiceImplUnderTest.countUntransittedByCourierId(0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testPageTransittingByCourierId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Configure OrderTrackingService.transitting(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<OrderTracking> orderTrackings = List.of(orderTracking);
        when(mockOrderTrackingService.transitting()).thenReturn(orderTrackings);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageTransittingByCourierId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageTransittingByCourierId_OrderTrackingServiceReturnsNoItems() {
        // Setup
        when(mockOrderTrackingService.transitting()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageTransittingByCourierId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testCountTransittingByCourierId() {
        // Setup
        // Configure OrderTrackingService.transitting(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setOrderId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<OrderTracking> orderTrackings = List.of(orderTracking);
        when(mockOrderTrackingService.transitting()).thenReturn(orderTrackings);

        // Run the test
        final long result = orderServiceImplUnderTest.countTransittingByCourierId(0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCountTransittingByCourierId_OrderTrackingServiceReturnsNoItems() {
        // Setup
        when(mockOrderTrackingService.transitting()).thenReturn(Collections.emptyList());

        // Run the test
        final long result = orderServiceImplUnderTest.countTransittingByCourierId(0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testPageArrivedByCourierId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageArrivedByCourierId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCountArrivedByCourierId() {
        assertThat(orderServiceImplUnderTest.countArrivedByCourierId(0L)).isEqualTo(0L);
    }

    @Test
    void testPageConfirmedByCourierId() {
        // Setup
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setDeliveryAddressId(0L);
        order.setOrderTrackingId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        order.setReviewId(0L);
        order.setServiceType(ServiceTypeEnum.TAKEOUT);
        order.setEstimatedArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Order> expectedResult = List.of(order);

        // Run the test
        final List<Order> result = orderServiceImplUnderTest.pageConfirmedByCourierId(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCountConfirmedByCourierId() {
        assertThat(orderServiceImplUnderTest.countConfirmedByCourierId(0L)).isEqualTo(0L);
    }

    @Test
    void testCheckStatus() {
        // Setup
        // Run the test
        orderServiceImplUnderTest.checkStatus();

        // Verify the results
        verify(mockOrderMapper).checkStatus();
    }
}
