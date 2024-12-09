package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Review;
import com.example.ordertaker.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService mockReviewService;

    @Test
    void testAdd() throws Exception {
        // Setup
        // Configure ReviewService.save(...).
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        when(mockReviewService.save(review)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/review/add")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testGetByClient() throws Exception {
        // Setup
        // Configure ReviewService.pageByClient(...).
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        final List<Review> reviews = List.of(review);
        when(mockReviewService.pageByClient(0L, 0L, 0L)).thenReturn(reviews);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/review/client")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"rating\":0.0,\"comment\":\"comment\",\"createdTime\":null}]}");
    }

    @Test
    void testGetByClient_ReviewServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockReviewService.pageByClient(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/review/client")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testCountByClientId() throws Exception {
        // Setup
        when(mockReviewService.countByClientId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/review/count/client")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testGetByCourier() throws Exception {
        // Setup
        // Configure ReviewService.pageByCourier(...).
        final Review review = new Review();
        review.setId(0L);
        review.setOrderId(0L);
        review.setRating(0.0);
        review.setComment("comment");
        review.setClientId(0L);
        final List<Review> reviews = List.of(review);
        when(mockReviewService.pageByCourier(0L, 0L, 0L)).thenReturn(reviews);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/review/courier")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"rating\":0.0,\"comment\":\"comment\",\"createdTime\":null}]}");
    }

    @Test
    void testGetByCourier_ReviewServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockReviewService.pageByCourier(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/review/courier")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testCountByCourierId() throws Exception {
        // Setup
        when(mockReviewService.countByCourierId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/review/count/courier")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        when(mockReviewService.removeById(0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/review/delete")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":false}");
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        when(mockReviewService.updateReview(0L, "comment", 0.0)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/review/update")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }
}
