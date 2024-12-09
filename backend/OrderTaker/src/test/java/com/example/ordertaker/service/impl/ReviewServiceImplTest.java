package com.example.ordertaker.service.impl;

import com.example.ordertaker.entity.Review;
import com.example.ordertaker.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private OrderService mockOrderService;

    @InjectMocks
    private ReviewServiceImpl reviewServiceImplUnderTest;

    @Test
    void testSave() {
        // Setup
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        review.setCourierId(0L);
        review.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        final boolean result = reviewServiceImplUnderTest.save(review);

        // Verify the results
        assertThat(result).isTrue();
        verify(mockOrderService).updateReviewId(0L, 0L);
    }

    @Test
    void testRemoveById() {
        // Setup
        when(mockOrderService.removeReviewId(0L)).thenReturn(false);

        // Run the test
        final boolean result = reviewServiceImplUnderTest.removeById(0L);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testUpdateReview() {
        assertThat(reviewServiceImplUnderTest.updateReview(0L, "comment", 0.0)).isFalse();
    }

    @Test
    void testPage() {
        // Setup
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        review.setCourierId(0L);
        review.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Review> expectedResult = List.of(review);

        // Run the test
        final List<Review> result = reviewServiceImplUnderTest.page(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageByClient() {
        // Setup
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        review.setCourierId(0L);
        review.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Review> expectedResult = List.of(review);

        // Run the test
        final List<Review> result = reviewServiceImplUnderTest.pageByClient(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPageByCourier() {
        // Setup
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        review.setCourierId(0L);
        review.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Review> expectedResult = List.of(review);

        // Run the test
        final List<Review> result = reviewServiceImplUnderTest.pageByCourier(0L, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCountByClientId() {
        assertThat(reviewServiceImplUnderTest.countByClientId(0L)).isEqualTo(0L);
    }

    @Test
    void testCountByCourierId() {
        assertThat(reviewServiceImplUnderTest.countByCourierId(0L)).isEqualTo(0L);
    }
}
