package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Message;
import com.example.ordertaker.service.MessageService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService mockMessageService;

    @Test
    void testAdd() throws Exception {
        // Setup
        // Configure MessageService.save(...).
        final Message message = new Message();
        message.setId(0L);
        message.setClientId(0L);
        message.setCourierId(0L);
        message.setContent("content");
        message.setSenderId(0L);
        when(mockMessageService.save(message)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/message")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testPage() throws Exception {
        // Setup
        // Configure MessageService.page(...).
        final Message message = new Message();
        message.setId(0L);
        message.setClientId(0L);
        message.setCourierId(0L);
        message.setContent("content");
        message.setSenderId(0L);
        final List<Message> messages = List.of(message);
        when(mockMessageService.page(0L, 0L, 0L, 0L)).thenReturn(messages);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/message")
                        .param("clientId", "0")
                        .param("courierId", "0")
                        .param("current", "0")
                        .param("size", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":[{\"id\":0,\"content\":\"content\",\"senderId\":0,\"time\":null}]}");
    }

    @Test
    void testPage_MessageServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMessageService.page(0L, 0L, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/message")
                        .param("clientId", "0")
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
    void testCount() throws Exception {
        // Setup
        when(mockMessageService.count(0L, 0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/message/count")
                        .param("clientId", "0")
                        .param("courierId", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":0}");
    }
}
