package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.constant.CurrentStatusEnum;
import com.example.ordertaker.entity.Order;
import com.github.yulichang.base.MPJBaseService;

import java.util.Date;
import java.util.List;

public interface OrderService extends IService<Order>, MPJBaseService<Order> {
    @Override
    boolean save(Order order);

    List<Order> pageByClientId(Long clientId, long current, long size);

    List<Order> pageByCourierId(Long clientId, long current, long size);

    List<Order> pageTakeoutByClientId(Long clientId, long current, long size);

    List<Order> pageExpressByClientId(Long clientId, long current, long size);

    List<Order> pageFlashByClientId(Long clientId, long current, long size);

    List<Order> unacceptedByClientId(Long clientId, long current, long size);

    List<Order> unacceptedAll(long current, long size);

    List<Order> pageUnconfirmedByClientId(Long clientId, long current, long size);

    List<Order> pageConfirmedByClientId(Long clientId, long current, long size);

    List<Order> pageCancelledByClientId(Long clientId, long current, long size);

    Date updateStatus(Long id, CurrentStatusEnum currentStatusEnum, Long courierId);

    boolean updateReviewId(Long orderId, Long reviewId);

    boolean removeReviewId(Long orderId);

    boolean updatePaymentId(Long orderId, Long paymentId);

    boolean updateOrderTrackingId(Long orderId, Long orderTrackingId);

    long countByClientId(Long clientId);

    long countByCourierId(Long courier);

    long countTakeoutByClientId(Long clientId);

    long countExpressByClientId(Long clientId);

    long countFlashByClientId(Long clientId);

    long countUnconfirmedByClientId(Long clientId);

    List<Order> pageUntransittedByCourierId(Long courierId, long current, long size);

    long countUntransittedByCourierId(Long courierId);

    List<Order> pageTransittingByCourierId(Long courierId, long current, long size);

    long countTransittingByCourierId(Long courierId);

    List<Order> pageArrivedByCourierId(Long courierId, long current, long size);

    long countArrivedByCourierId(Long courierId);

    List<Order> pageConfirmedByCourierId(Long courierId, long current, long size);

    long countConfirmedByCourierId(Long courierId);

    void checkStatus();
}
