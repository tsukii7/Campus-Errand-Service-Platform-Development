package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ordertaker.entity.Client;
import com.example.ordertaker.mapper.ClientMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientMapper mockClientMapper;

    @InjectMocks
    private ClientServiceImpl clientServiceImplUnderTest;

    @Test
    void testCheckClientExist() {
        // Setup
        final Client client = new Client(0L, "clientName", "avatarUrl", "email", "password");
        final Client expectedResult = new Client(0L, "clientName", "avatarUrl", "email", "password");

        // Configure ClientMapper.selectOne(...).
        final Client client1 = new Client(0L, "clientName", "avatarUrl", "email", "password");
        when(mockClientMapper.selectOne(any(LambdaQueryWrapper.class))).thenReturn(client1);

        // Run the test
        final Client result = clientServiceImplUnderTest.selectOne("email","password");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave() {
        // Setup
        final Client client = new Client(0L, "clientName", "avatarUrl", null, "password");

        //Assertions.assertThrows()
        Exception ex = Assertions.assertThrows(Exception.class, () -> clientServiceImplUnderTest.save(client));

        //Assertions.assertEquals()
        Assertions.assertEquals(ex.getMessage(), "用户信息不完整！");
    }

    @Test
    void testInsertClient() {
        // Setup
        final Client client = new Client(0L, "clientName", "avatarUrl", "email", "password");

        // Run the test
        clientServiceImplUnderTest.save(client);

        // Verify the results
        verify(mockClientMapper).insert(new Client(0L, "clientName", "avatarUrl", "email", "password"));
    }
}
