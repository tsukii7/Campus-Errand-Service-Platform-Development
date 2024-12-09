package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.OrderTracking;

import java.util.Date;
import java.util.List;

public interface OrderTrackingService extends IService<OrderTracking> {
    @Override boolean save(OrderTracking orderTracking);

    boolean updatePlacedTime(Long id, Date placedTime);

    boolean updateTransitedTime(Long id, Date transitedTime);

    boolean updateArrivedTime(Long id, Date arrivedTime);

    boolean updateConfirmedTime(Long id, Date confirmedTime);

    boolean updateCancelledTime(Long id, Date cancelledTime);

    OrderTracking getById(Long id);

    List<OrderTracking> transitting();

    List<OrderTracking> untransitted();
}
