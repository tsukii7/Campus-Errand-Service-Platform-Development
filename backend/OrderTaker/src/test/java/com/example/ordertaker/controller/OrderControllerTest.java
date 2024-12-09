package com.example.ordertaker.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ordertaker.constant.CurrentStatusEnum;
import com.example.ordertaker.entity.Order;
import com.example.ordertaker.service.OrderService;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService mockOrderService;

    @Test
    void testCountUnacceptedAll() throws Exception {
        // Setup
        when(mockOrderService.count(any(LambdaQueryWrapper.class))).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/unaccepted")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testConfirmed() throws Exception {
        // Setup
        // Configure OrderService.pageConfirmedByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageConfirmedByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/confirmed")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testConfirmed_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageConfirmedByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/confirmed")
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
    void testCountFinished() throws Exception {
        // Setup
        when(mockOrderService.count(any(LambdaQueryWrapper.class))).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/confirmed")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testCancelled() throws Exception {
        // Setup
        // Configure OrderService.pageCancelledByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageCancelledByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/cancelled")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testCancelled_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageCancelledByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/cancelled")
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
    void testCountCanceled() throws Exception {
        // Setup
        when(mockOrderService.count(any(LambdaQueryWrapper.class))).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/cancelled")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testAllByClientId() throws Exception {
        // Setup
        // Configure OrderService.pageByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/all")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testAllByClientId_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/all")
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
        when(mockOrderService.countByClientId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/all")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testTakeout() throws Exception {
        // Setup
        // Configure OrderService.pageTakeoutByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageTakeoutByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/takeout")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testTakeout_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageTakeoutByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/takeout")
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
    void testCountTakeout() throws Exception {
        // Setup
        when(mockOrderService.countTakeoutByClientId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/takeout")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testExpress() throws Exception {
        // Setup
        // Configure OrderService.pageExpressByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageExpressByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/express")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testExpress_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageExpressByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/express")
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
    void testCountExpress() throws Exception {
        // Setup
        when(mockOrderService.countExpressByClientId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/express")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testFlash() throws Exception {
        // Setup
        // Configure OrderService.pageFlashByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageFlashByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/flash")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testFlash_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageFlashByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/flash")
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
    void testCountFlash() throws Exception {
        // Setup
        when(mockOrderService.countFlashByClientId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/flash")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testUnacceptedByClientId() throws Exception {
        // Setup
        // Configure OrderService.unacceptedByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.unacceptedByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/unaccepted")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testUnacceptedByClientId_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.unacceptedByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/unaccepted")
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
    void testCountUnacceptedClient() throws Exception {
        // Setup
        when(mockOrderService.count(any(LambdaQueryWrapper.class))).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/unaccepted")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testUnacceptedAll() throws Exception {
        // Setup
        // Configure OrderService.unacceptedAll(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.unacceptedAll(0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/unaccepted")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testUnacceptedAll_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.unacceptedAll(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/unaccepted")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testUnconfirmed() throws Exception {
        // Setup
        // Configure OrderService.pageUnconfirmedByClientId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageUnconfirmedByClientId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/unconfirmed")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testUnconfirmed_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageUnconfirmedByClientId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/client/unconfirmed")
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
    void testCountUnconfirmed() throws Exception {
        // Setup
        when(mockOrderService.countUnconfirmedByClientId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/client/unconfirmed")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testAllByCourierId() throws Exception {
        // Setup
        // Configure OrderService.pageByCourierId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageByCourierId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/all")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testAllByCourierId_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageByCourierId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/all")
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
        when(mockOrderService.countByCourierId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/courier/all")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testUntransittedByCourier() throws Exception {
        // Setup
        // Configure OrderService.pageUntransittedByCourierId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageUntransittedByCourierId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/untransitted")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testUntransittedByCourier_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageUntransittedByCourierId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/untransitted")
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
    void testCountUntransittedByCourier() throws Exception {
        // Setup
        when(mockOrderService.countUntransittedByCourierId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/courier/untransitted")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testTransittingByCourier() throws Exception {
        // Setup
        // Configure OrderService.pageTransittingByCourierId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageTransittingByCourierId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/transitting")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testTransittingByCourier_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageTransittingByCourierId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/transitting")
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
    void testCountTransittingByCourier() throws Exception {
        // Setup
        when(mockOrderService.countTransittingByCourierId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/courier/transitting")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testArrivedByCourier() throws Exception {
        // Setup
        // Configure OrderService.pageArrivedByCourierId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageArrivedByCourierId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/arrived")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testArrivedByCourier_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageArrivedByCourierId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/arrived")
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
    void testCountArrivedByCourier() throws Exception {
        // Setup
        when(mockOrderService.countArrivedByCourierId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/courier/arrived")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testConfirmedByCourier() throws Exception {
        // Setup
        // Configure OrderService.pageConfirmedByCourierId(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        final List<Order> orders = List.of(order);
        when(mockOrderService.pageConfirmedByCourierId(0L, 0L, 0L)).thenReturn(orders);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/confirmed")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"courierId\":0,\"paymentId\":0,\"pickUpAddress\":null,\"deliveryAddressId\":null,\"orderTrackingId\":null,\"currentStatus\":\"å·²ä¸\u008Bå\u008D\u0095\",\"reviewId\":null,\"serviceType\":null,\"expectedArrivedTime\":null,\"fee\":null,\"description\":null,\"restaurant\":null,\"estimatedArrivedTime\":null,\"totalPrice\":null,\"pickUpCode\":null,\"size\":null,\"weight\":null}]}");
    }

    @Test
    void testConfirmedByCourier_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockOrderService.pageConfirmedByCourierId(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/courier/confirmed")
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
    void testCountConfirmedByCourier() throws Exception {
        // Setup
        when(mockOrderService.countConfirmedByCourierId(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/order/count/courier/confirmed")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testStatus() throws Exception {
        // Setup
        // Configure OrderService.updateStatus(...).
        final Date date = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        when(mockOrderService.updateStatus(0L, CurrentStatusEnum.PLACED, 0L)).thenReturn(date);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/order/status")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testReviewId() throws Exception {
        // Setup
        when(mockOrderService.updateReviewId(0L, 0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/order/review")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testPaymentId() throws Exception {
        // Setup
        when(mockOrderService.updatePaymentId(0L, 0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/order/payment")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testAdd() throws Exception {
        // Setup
        // Configure OrderService.save(...).
        final Order order = new Order();
        order.setId(0L);
        order.setClientId(0L);
        order.setCourierId(0L);
        order.setPaymentId(0L);
        order.setCurrentStatus(CurrentStatusEnum.PLACED);
        when(mockOrderService.save(order)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/order/add")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }
}
