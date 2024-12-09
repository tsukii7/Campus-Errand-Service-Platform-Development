package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.service.CourierService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class CourierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierService mockCourierService;

    @Test
    void testLogin() throws Exception {
        // Setup
        // Configure CourierService.selectOne(...).
        final Courier courier = new Courier();
        courier.setId(0L);
        courier.setCourierName("courierName");
        courier.setAvatarUrl("avatarUrl");
        courier.setEmail("email");
        courier.setPassword("password");
        when(mockCourierService.selectOne("email", "password")).thenReturn(courier);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/courier/login")
                        .param("email", "email")
                        .param("password", "password")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":{\"id\":0,\"courierName\":\"courierName\",\"avatarUrl\":\"avatarUrl\",\"email\":\"email\",\"password\":\"password\",\"rating\":null,\"blockTimes\":null}}");
    }

    @Test
    void testLogin_CourierServiceReturnsNull() throws Exception {
        // Setup
        when(mockCourierService.selectOne("email", "password")).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/courier/login")
                        .param("email", "email")
                        .param("password", "password")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"-1\",\"message\":\"é\u0082®ç®±æ\u0088\u0096å¯\u0086ç \u0081é\u0094\u0099è¯¯\",\"result\":null}");
    }

    @Test
    void testRegister() throws Exception {
        // Setup
        // Configure CourierService.save(...).
        final Courier courier = new Courier();
        courier.setId(0L);
        courier.setCourierName("courierName");
        courier.setAvatarUrl("avatarUrl");
        courier.setEmail("email");
        courier.setPassword("password");
        when(mockCourierService.save(courier)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/courier/register")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testIsBlock() throws Exception {
        // Setup
        when(mockCourierService.isBlock(0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/courier/check-block")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":false}");
    }

    @Test
    void testGet() throws Exception {
        // Setup
        // Configure CourierService.getById(...).
        final Courier courier = new Courier();
        courier.setId(0L);
        courier.setCourierName("courierName");
        courier.setAvatarUrl("avatarUrl");
        courier.setEmail("email");
        courier.setPassword("password");
        when(mockCourierService.getById(0L)).thenReturn(courier);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/courier/get")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":{\"id\":0,\"courierName\":\"courierName\",\"avatarUrl\":\"avatarUrl\",\"email\":\"email\",\"password\":\"password\",\"rating\":null,\"blockTimes\":null}}");
    }
}


