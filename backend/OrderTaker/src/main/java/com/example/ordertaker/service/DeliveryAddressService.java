package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService extends IService<DeliveryAddress> {
    List<DeliveryAddress> page(long clientId, long current, long size);

    boolean save(DeliveryAddress deliveryAddress);

    long count(long clientId);
}
