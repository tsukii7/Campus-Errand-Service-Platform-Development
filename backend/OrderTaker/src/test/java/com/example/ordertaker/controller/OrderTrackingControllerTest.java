package com.example.ordertaker.controller;

import com.example.ordertaker.entity.OrderTracking;
import com.example.ordertaker.service.OrderTrackingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class OrderTrackingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderTrackingService mockOrderTrackingService;

    @Test
    void testAdd() throws Exception {
        // Setup
        // Configure OrderTrackingService.save(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockOrderTrackingService.save(orderTracking)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/track")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testGetById() throws Exception {
        // Setup
        // Configure OrderTrackingService.getById(...).
        final OrderTracking orderTracking = new OrderTracking();
        orderTracking.setId(0L);
        orderTracking.setPlacedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setTransitedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setArrivedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setConfirmedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        orderTracking.setCancelledTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockOrderTrackingService.getById(0L)).thenReturn(orderTracking);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/track")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":{\"id\":0,\"createdTime\":null,\"placedTime\":\"2019-12-31T16:00:00.000+00:00\",\"transitedTime\":\"2019-12-31T16:00:00.000+00:00\",\"arrivedTime\":\"2019-12-31T16:00:00.000+00:00\",\"confirmedTime\":\"2019-12-31T16:00:00.000+00:00\",\"cancelledTime\":\"2019-12-31T16:00:00.000+00:00\"}}");
    }

    @Test
    void testPlace() throws Exception {
        // Setup
        when(mockOrderTrackingService.updatePlacedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/track/place")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testTransit() throws Exception {
        // Setup
        when(mockOrderTrackingService.updateTransitedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/track/transit")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testArrive() throws Exception {
        // Setup
        when(mockOrderTrackingService.updateArrivedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/track/arrive")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testConfirm() throws Exception {
        // Setup
        when(mockOrderTrackingService.updateConfirmedTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/track/confirm")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testCancel() throws Exception {
        // Setup
        when(mockOrderTrackingService.updateCancelledTime(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/track/cancel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }
}
