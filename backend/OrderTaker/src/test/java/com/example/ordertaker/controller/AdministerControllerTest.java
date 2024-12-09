package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Administer;
import com.example.ordertaker.entity.Blacklist;
import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.service.AdministerService;
import com.example.ordertaker.service.BlacklistService;
import com.example.ordertaker.service.CourierService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AdministerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdministerService mockAdministerService;
    @MockBean
    private CourierService mockCourierService;
    @MockBean
    private BlacklistService mockBlacklistService;

    @Test
    void testLogin() throws Exception {
        // Setup
        // Configure AdministerService.checkAdministerExist(...).
        final Administer administer = new Administer(0L, "adminName", "password");
        when(mockAdministerService.checkAdministerExist("adminName", "password")).thenReturn(administer);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/login")
                        .param("adminName", "adminName")
                        .param("password", "password")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testLogin_AdministerServiceReturnsNull() throws Exception {
        // Setup
        when(mockAdministerService.checkAdministerExist("adminName", "password")).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/login")
                        .param("adminName", "adminName")
                        .param("password", "password")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"-1\",\"message\":\"ç®¡ç\u0090\u0086å\u0091\u0098ç\u0094¨æ\u0088·å\u0090\u008Dæ\u0088\u0096è\u0080\u0085å¯\u0086ç \u0081é\u0094\u0099è¯¯ï¼\u0081\",\"result\":null}");
    }

    @Test
    void testBlock() throws Exception {
        // Setup
        when(mockCourierService.block(0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/administer/block")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testUnblock() throws Exception {
        // Setup
        when(mockCourierService.unblock(0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/administer/unblock")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":false}");
    }

    @Test
    void testSetBlockTimes() throws Exception {
        // Setup
        when(mockCourierService.setBlockTimes(0L, 0)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/administer/block-times")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testSetRating() throws Exception {
        // Setup
        when(mockCourierService.setRating(0L, 0.0)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/administer/rating")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testGetCouriers() throws Exception {
        // Setup
        // Configure CourierService.page(...).
        final Courier courier = new Courier();
        courier.setId(0L);
        courier.setCourierName("courierName");
        courier.setAvatarUrl("avatarUrl");
        courier.setRating(0.0);
        courier.setBlockTimes(0);
        final List<Courier> couriers = List.of(courier);
        when(mockCourierService.page(0L, 0L)).thenReturn(couriers);

        // Configure CourierService.pageOrderByRatingAsc(...).
        final Courier courier1 = new Courier();
        courier1.setId(0L);
        courier1.setCourierName("courierName");
        courier1.setAvatarUrl("avatarUrl");
        courier1.setRating(0.0);
        courier1.setBlockTimes(0);
        final List<Courier> couriers1 = List.of(courier1);
        when(mockCourierService.pageOrderByRatingAsc(0L, 0L)).thenReturn(couriers1);

        // Configure CourierService.pageOrderByRatingDesc(...).
        final Courier courier2 = new Courier();
        courier2.setId(0L);
        courier2.setCourierName("courierName");
        courier2.setAvatarUrl("avatarUrl");
        courier2.setRating(0.0);
        courier2.setBlockTimes(0);
        final List<Courier> couriers2 = List.of(courier2);
        when(mockCourierService.pageOrderByRatingDesc(0L, 0L)).thenReturn(couriers2);

        // Configure CourierService.pageOrderByBlockTimesAsc(...).
        final Courier courier3 = new Courier();
        courier3.setId(0L);
        courier3.setCourierName("courierName");
        courier3.setAvatarUrl("avatarUrl");
        courier3.setRating(0.0);
        courier3.setBlockTimes(0);
        final List<Courier> couriers3 = List.of(courier3);
        when(mockCourierService.pageOrderByBlockTimesAsc(0L, 0L)).thenReturn(couriers3);

        // Configure CourierService.pageOrderByBlockTimesDesc(...).
        final Courier courier4 = new Courier();
        courier4.setId(0L);
        courier4.setCourierName("courierName");
        courier4.setAvatarUrl("avatarUrl");
        courier4.setRating(0.0);
        courier4.setBlockTimes(0);
        final List<Courier> couriers4 = List.of(courier4);
        when(mockCourierService.pageOrderByBlockTimesDesc(0L, 0L)).thenReturn(couriers4);

        // Configure CourierService.page(...).
        final Courier courier5 = new Courier();
        courier5.setId(0L);
        courier5.setCourierName("courierName");
        courier5.setAvatarUrl("avatarUrl");
        courier5.setRating(0.0);
        courier5.setBlockTimes(0);
        final List<Courier> couriers5 = List.of(courier5);
        when(mockCourierService.page("courierName", 0L, 0L)).thenReturn(couriers5);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"courierName\":\"courierName\",\"avatarUrl\":\"avatarUrl\",\"email\":null,\"password\":null,\"rating\":0.0,\"blockTimes\":0}]}");
    }

    @Test
    void testGetCouriers_CourierServicePage1ReturnsNoItems() throws Exception {
        // Setup
        when(mockCourierService.page(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetCouriers_CourierServicePageOrderByRatingAscReturnsNoItems() throws Exception {
        // Setup
        when(mockCourierService.pageOrderByRatingAsc(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetCouriers_CourierServicePageOrderByRatingDescReturnsNoItems() throws Exception {
        // Setup
        when(mockCourierService.pageOrderByRatingDesc(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetCouriers_CourierServicePageOrderByBlockTimesAscReturnsNoItems() throws Exception {
        // Setup
        when(mockCourierService.pageOrderByBlockTimesAsc(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetCouriers_CourierServicePageOrderByBlockTimesDescReturnsNoItems() throws Exception {
        // Setup
        when(mockCourierService.pageOrderByBlockTimesDesc(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetCouriers_CourierServicePage2ReturnsNoItems() throws Exception {
        // Setup
        when(mockCourierService.page("courierName", 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/couriers")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetCouriersCount() throws Exception {
        // Setup
        when(mockCourierService.count()).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/count/couriers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }

    @Test
    void testGetBlacklists() throws Exception {
        // Setup
        // Configure BlacklistService.page(...).
        final List<Blacklist> blacklists = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));
        when(mockBlacklistService.page(0L, 0L)).thenReturn(blacklists);

        // Configure BlacklistService.pageOrderByCreatedTimeAsc(...).
        final List<Blacklist> blacklists1 = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));
        when(mockBlacklistService.pageOrderByCreatedTimeAsc(0L, 0L)).thenReturn(blacklists1);

        // Configure BlacklistService.pageOrderByCreatedTimeDesc(...).
        final List<Blacklist> blacklists2 = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));
        when(mockBlacklistService.pageOrderByCreatedTimeDesc(0L, 0L)).thenReturn(blacklists2);

        // Configure BlacklistService.page(...).
        final List<Blacklist> blacklists3 = List.of(
                new Blacklist(0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0));
        when(mockBlacklistService.page("courierName", 0L, 0L)).thenReturn(blacklists3);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/blacklists")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"courierId\":0,\"createdTime\":\"2019-12-31T16:00:00.000+00:00\",\"deleted\":0}]}");
    }

    @Test
    void testGetBlacklists_BlacklistServicePage1ReturnsNoItems() throws Exception {
        // Setup
        when(mockBlacklistService.page(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/blacklists")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetBlacklists_BlacklistServicePageOrderByCreatedTimeAscReturnsNoItems() throws Exception {
        // Setup
        when(mockBlacklistService.pageOrderByCreatedTimeAsc(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/blacklists")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetBlacklists_BlacklistServicePageOrderByCreatedTimeDescReturnsNoItems() throws Exception {
        // Setup
        when(mockBlacklistService.pageOrderByCreatedTimeDesc(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/blacklists")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetBlacklists_BlacklistServicePage2ReturnsNoItems() throws Exception {
        // Setup
        when(mockBlacklistService.page("courierName", 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/blacklists")
                        .param("current", "0")
                        .param("size", "0")
                        .param("courierName", "courierName")
                        .param("order", "order")
                        .param("type", "type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[]}");
    }

    @Test
    void testGetBlacklistsCount() throws Exception {
        // Setup
        when(mockBlacklistService.count()).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/administer/count/blacklists")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }
}
