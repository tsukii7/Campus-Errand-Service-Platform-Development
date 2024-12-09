package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.OrderTracking;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.OrderTrackingMapper;
import com.example.ordertaker.service.OrderService;
import com.example.ordertaker.service.OrderTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderTrackingServiceImpl extends ServiceImpl<OrderTrackingMapper, OrderTracking> implements OrderTrackingService {

    @Lazy
    @Autowired
    OrderService orderService;

    @Override
    public boolean save(OrderTracking orderTracking) {
        if (orderTracking.getOrderId() == null)
            throw new MyException("订单跟踪信息不完整");

        if (orderTracking.getCreatedTime() == null)
            orderTracking.setCreatedTime(new Date());

        if (super.save(orderTracking))
            return orderService.updateOrderTrackingId(orderTracking.getOrderId(), orderTracking.getId());
        else
            return false;
    }


    @Override
    public boolean updatePlacedTime(Long id, Date placedTime) {
        if (id == null || placedTime == null)
            throw new MyException("订单跟踪信息不完整");

        return super.update(
                new LambdaUpdateWrapper<OrderTracking>()
                        .eq(OrderTracking::getOrderId, id)
                        .set(OrderTracking::getPlacedTime, placedTime));
    }

    @Override
    public boolean updateTransitedTime(Long id, Date transitedTime) {
        if (id == null || transitedTime == null)
            throw new MyException("订单跟踪信息不完整");

        return super.update(
                new LambdaUpdateWrapper<OrderTracking>()
                        .eq(OrderTracking::getOrderId, id)
                        .set(OrderTracking::getTransitedTime, transitedTime));
    }

    @Override
    public boolean updateArrivedTime(Long id, Date arrivedTime) {
        if (id == null || arrivedTime == null)
            throw new MyException("订单跟踪信息不完整");

        return super.update(
                new LambdaUpdateWrapper<OrderTracking>()
                        .eq(OrderTracking::getOrderId, id)
                        .set(OrderTracking::getArrivedTime, arrivedTime));
    }

    @Override
    public boolean updateConfirmedTime(Long id, Date confirmedTime) {
        if (id == null || confirmedTime == null)
            throw new MyException("订单跟踪信息不完整");

        return super.update(
                new LambdaUpdateWrapper<OrderTracking>()
                        .eq(OrderTracking::getOrderId, id)
                        .set(OrderTracking::getConfirmedTime, confirmedTime));
    }

    @Override
    public boolean updateCancelledTime(Long id, Date cancelledTime) {
        if (id == null || cancelledTime == null)
            throw new MyException("订单跟踪信息不完整");

        return super.update(
                new LambdaUpdateWrapper<OrderTracking>()
                        .eq(OrderTracking::getOrderId, id)
                        .set(OrderTracking::getCancelledTime, cancelledTime));
    }

    @Override
    public OrderTracking getById(Long id) {
        if (id == null)
            throw new MyException("订单跟踪信息不完整");

        return super.getById(id);
    }

    @Override
    public List<OrderTracking> transitting() {
        return super.baseMapper.selectList(
                new LambdaQueryWrapper<OrderTracking>()
                        .isNotNull(OrderTracking::getTransitedTime)
                        .isNull(OrderTracking::getArrivedTime)
        );
    }

    @Override
    public List<OrderTracking> untransitted() {
        return super.baseMapper.selectList(
                new LambdaQueryWrapper<OrderTracking>()
                        .isNull(OrderTracking::getTransitedTime)
                        .isNotNull(OrderTracking::getCancelledTime)
        );
    }
}
