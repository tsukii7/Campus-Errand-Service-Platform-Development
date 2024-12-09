package com.example.ordertaker.service.impl;

import com.example.ordertaker.entity.DeliveryAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryAddressServiceImplTest {

    private DeliveryAddressServiceImpl deliveryAddressServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        deliveryAddressServiceImplUnderTest = new DeliveryAddressServiceImpl();
    }

    @Test
    void testPage() {
        // Setup
        final List<DeliveryAddress> expectedResult = List.of(
                new DeliveryAddress(0L, 0L, "address", "phoneNumber", "receiver"));

        // Run the test
        final List<DeliveryAddress> result = deliveryAddressServiceImplUnderTest.page(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave() {
        // Setup
        final DeliveryAddress deliveryAddress = new DeliveryAddress(0L, 0L, "address", "phoneNumber", "receiver");

        // Run the test
        final boolean result = deliveryAddressServiceImplUnderTest.save(deliveryAddress);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testCount() {
        assertThat(deliveryAddressServiceImplUnderTest.count(0L)).isEqualTo(0L);
    }
}
