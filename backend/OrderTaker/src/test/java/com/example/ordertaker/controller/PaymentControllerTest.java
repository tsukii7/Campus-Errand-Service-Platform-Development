package com.example.ordertaker.controller;

import com.example.ordertaker.constant.PaymentStatusEnum;
import com.example.ordertaker.entity.Payment;
import com.example.ordertaker.service.PaymentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService mockPaymentService;

    @Test
    void testAdd() throws Exception {
        // Setup
        // Configure PaymentService.save(...).
        final Payment payment = new Payment();
        payment.setId(0L);
        payment.setTransactionId("transactionId");
        payment.setOrderId(0L);
        payment.setStatus(PaymentStatusEnum.PENDING_PAYMENT);
        payment.setCreatedTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockPaymentService.save(payment)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/payment/add")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        when(mockPaymentService.updateStatus(0L, PaymentStatusEnum.PENDING_PAYMENT)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/payment/update")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":\"4000\",\"message\":\"è¯·æ±\u0082ç\u009A\u0084æ\u0095°æ\u008D®æ ¼å¼\u008Fä¸\u008Dç¬¦!\",\"result\":null}");
    }

    @Test
    void testGetStatus() throws Exception {
        // Setup
        when(mockPaymentService.getStatus(0L)).thenReturn(PaymentStatusEnum.PENDING_PAYMENT);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/payment/status")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"code\":0,\"message\":\"ok\",\"data\":\"å¾\u0085æ\u0094¯ä»\u0098\"}");
    }
}


