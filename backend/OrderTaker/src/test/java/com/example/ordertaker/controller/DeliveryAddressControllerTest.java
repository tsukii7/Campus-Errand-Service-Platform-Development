package com.example.ordertaker.controller;

import com.example.ordertaker.entity.DeliveryAddress;
import com.example.ordertaker.service.DeliveryAddressService;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryAddressService mockDeliveryAddressService;

    @Test
    void testAddDeliveryAddress() throws Exception {
        // Setup
        when(mockDeliveryAddressService.save(
                new DeliveryAddress(0L, 0L, "address", "phoneNumber", "receiver"))).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/deliveryAddress/add")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testGet() throws Exception {
        // Setup
        // Configure DeliveryAddressService.getById(...).
        final DeliveryAddress deliveryAddress = new DeliveryAddress(0L, 0L, "address", "phoneNumber", "receiver");
        when(mockDeliveryAddressService.getById(0L)).thenReturn(deliveryAddress);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/deliveryAddress/get")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":{\"id\":0,\"clientId\":0,\"address\":\"address\",\"phoneNumber\":\"phoneNumber\",\"receiver\":\"receiver\"}}");
    }

    @Test
    void testPage() throws Exception {
        // Setup
        // Configure DeliveryAddressService.page(...).
        final List<DeliveryAddress> deliveryAddresses = List.of(
                new DeliveryAddress(0L, 0L, "address", "phoneNumber", "receiver"));
        when(mockDeliveryAddressService.page(0L, 0L, 0L)).thenReturn(deliveryAddresses);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/deliveryAddress/page")
                        .param("clientId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"clientId\":0,\"address\":\"address\",\"phoneNumber\":\"phoneNumber\",\"receiver\":\"receiver\"}]}");
    }

    @Test
    void testPage_DeliveryAddressServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockDeliveryAddressService.page(0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/deliveryAddress/page")
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
    void testCount() throws Exception {
        // Setup
        when(mockDeliveryAddressService.count(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/deliveryAddress/count")
                        .param("clientId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        when(mockDeliveryAddressService.removeById(0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/deliveryAddress/delete")
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
        when(mockDeliveryAddressService.updateById(
                new DeliveryAddress(0L, 0L, "address", "phoneNumber", "receiver"))).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/deliveryAddress/update")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }
}
