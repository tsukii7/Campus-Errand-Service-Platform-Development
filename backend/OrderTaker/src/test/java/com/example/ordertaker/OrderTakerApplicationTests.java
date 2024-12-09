package com.example.ordertaker;


import com.example.ordertaker.entity.Client;
import com.example.ordertaker.entity.DeliveryAddress;
import com.example.ordertaker.mapper.ClientMapper;
import com.example.ordertaker.mapper.DeliveryAddressMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderTakerApplicationTests {
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;
    @Autowired
    private ClientMapper clientMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testClientInsert() {
        Client tsuki = new Client();
        tsuki.setClientName("tki");
        tsuki.setPassword("123456");
        tsuki.setAvatarUrl("bilibili.com");
        tsuki.setEmail("17123@qq.com");

        clientMapper.insert(tsuki);
    }
    @Test
    public void testClientQuery() {
//        Client tsuki = clientMapper.selectById(1);
        Client tsuki = clientMapper.selectList(null).get(0);
        System.out.println(tsuki);
    }

    @Test
    public void testDeliveryAddressInsert() {
        Client tsuki = clientMapper.selectList(null).get(0);
        System.out.println(tsuki);
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setClientId(1l);
        deliveryAddress.setAddress("test address");
        deliveryAddress.setPhoneNumber("123456789");
        deliveryAddress.setReceiver("test receiver");
        System.out.println(deliveryAddress);
        deliveryAddressMapper.insert(deliveryAddress);
    }


}
