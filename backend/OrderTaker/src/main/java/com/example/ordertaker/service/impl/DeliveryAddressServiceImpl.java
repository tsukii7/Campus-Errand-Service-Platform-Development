package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.DeliveryAddress;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.DeliveryAddressMapper;
import com.example.ordertaker.service.DeliveryAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServiceImpl extends ServiceImpl<DeliveryAddressMapper, DeliveryAddress> implements DeliveryAddressService {

    @Override
    public List<DeliveryAddress> page(long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<DeliveryAddress>()
                        .eq(DeliveryAddress::getClientId, clientId)
        ).getRecords();
    }

    @Override
    public boolean save(DeliveryAddress deliveryAddress) {
        if (deliveryAddress.getClientId() == null
                || deliveryAddress.getAddress() == null || deliveryAddress.getAddress().isEmpty()
                || deliveryAddress.getReceiver() == null || deliveryAddress.getReceiver().isEmpty()
                || deliveryAddress.getPhoneNumber() == null || deliveryAddress.getPhoneNumber().isEmpty())
            throw new MyException("配送地址信息不完整！");

        return super.save(deliveryAddress);
    }

    @Override
    public long count(long clientId) {
        return super.count(
                new LambdaQueryWrapper<DeliveryAddress>()
                        .eq(DeliveryAddress::getClientId, clientId)
        );
    }
}

