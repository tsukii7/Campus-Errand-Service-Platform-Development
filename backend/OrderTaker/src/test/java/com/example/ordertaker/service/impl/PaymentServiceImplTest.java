package com.example.ordertaker.service.impl;

import com.example.ordertaker.constant.PaymentStatusEnum;
import com.example.ordertaker.entity.Payment;
import com.example.ordertaker.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PaymentServiceImplTest {

    private PaymentServiceImpl paymentServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        paymentServiceImplUnderTest = new PaymentServiceImpl();
        paymentServiceImplUnderTest.orderService = mock(OrderService.class);
    }

    @Test
    void testSave() {
        // Setup
        final Payment payment = new Payment();
        payment.setId(0L);
        payment.setTransactionId("transactionId");
        payment.setOrderId(0L);
        payment.setStatus(PaymentStatusEnum.PENDING_PAYMENT);
        payment.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        final boolean result = paymentServiceImplUnderTest.save(payment);

        // Verify the results
        assertThat(result).isTrue();
        verify(paymentServiceImplUnderTest.orderService).updatePaymentId(0L, 0L);
    }

    @Test
    void testUpdateStatus() {
        assertThat(paymentServiceImplUnderTest.updateStatus(0L, PaymentStatusEnum.PENDING_PAYMENT)).isFalse();
    }

    @Test
    void testGetStatus() {
        assertThat(paymentServiceImplUnderTest.getStatus(0L)).isEqualTo(PaymentStatusEnum.PENDING_PAYMENT);
    }
}
