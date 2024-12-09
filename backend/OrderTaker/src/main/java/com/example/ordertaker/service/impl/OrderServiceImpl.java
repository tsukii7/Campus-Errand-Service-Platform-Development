package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.constant.CurrentStatusEnum;
import com.example.ordertaker.constant.ServiceTypeEnum;
import com.example.ordertaker.entity.Order;
import com.example.ordertaker.entity.OrderTracking;
import com.example.ordertaker.mapper.OrderMapper;
import com.example.ordertaker.service.OrderService;
import com.example.ordertaker.service.OrderTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderTrackingService orderTrackingService;

    // @Autowired
    // private OrderMapper orderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean save(Order order) {
        if (order.getClientId() == null
                || order.getDeliveryAddressId() == null)
            throw new MyException("订单信息不完整");

        super.save(order);

        if (order.getCurrentStatus() == null)
            order.setCurrentStatus(CurrentStatusEnum.PLACED);

        if (order.getOrderTrackingId() == null) {
            OrderTracking orderTracking = new OrderTracking();
            orderTracking.setOrderId(order.getId());
            if (order.getServiceType() == ServiceTypeEnum.TAKEOUT) {
                orderTracking.setPlacedTime(order.getEstimatedArrivedTime());
            }
            orderTrackingService.save(orderTracking);
            order.setOrderTrackingId(orderTracking.getId());
        }

        return super.updateById(order);
    }

    @Override
    public List<Order> pageByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
        ).getRecords();
    }

    @Override
    public List<Order> pageByCourierId(Long courierId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCourierId, courierId)
        ).getRecords();
    }

    @Override
    public List<Order> pageTakeoutByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getServiceType, ServiceTypeEnum.TAKEOUT)
        ).getRecords();
    }

    @Override
    public List<Order> pageExpressByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getServiceType, ServiceTypeEnum.EXPRESS)
        ).getRecords();
    }

    @Override
    public List<Order> pageFlashByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getServiceType, ServiceTypeEnum.FLASH)
        ).getRecords();
    }

    @Override
    public List<Order> unacceptedByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.PLACED)
        ).getRecords();
    }

    @Override
    public List<Order> unacceptedAll(long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.PLACED)
        ).getRecords();
    }

    @Override
    public List<Order> pageUnconfirmedByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .and(orderLambdaQueryWrapper -> orderLambdaQueryWrapper
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.ACCEPTED)
                                .or()
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.TRANSITING)
                                .or()
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.OVERDUE)
                                .or()
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.DELIVERED)
                        )
        ).getRecords();
    }

    @Override
    public List<Order> pageConfirmedByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.CONFIRMED)
        ).getRecords();
    }

    @Override
    public List<Order> pageCancelledByClientId(Long clientId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.CANCELLED)
        ).getRecords();
    }

    @Override
    public Date updateStatus(Long id, CurrentStatusEnum currentStatus, Long courierId) {
        if (id == null || currentStatus == null)
            throw new MyException("订单信息不完整");

        OrderTracking orderTracking = orderTrackingService.getById(id);
        Order order = super.getById(id);
        if (!(currentStatus == CurrentStatusEnum.TRANSITING && order.getCurrentStatus() == CurrentStatusEnum.OVERDUE))
            order.setCurrentStatus(currentStatus);

        boolean succeed = super.updateById(order);

        if (succeed) {
            if (orderTracking == null)
                throw new MyException("订单跟踪信息不存在");

            Date time = new Date();
            if (currentStatus == CurrentStatusEnum.ACCEPTED) {
                assert (courierId != null);
                order.setCourierId(courierId);
            } else if (currentStatus == CurrentStatusEnum.TRANSITING) {
                orderTracking.setTransitedTime(time);
            } else if (currentStatus == CurrentStatusEnum.DELIVERED) {
                orderTracking.setArrivedTime(time);
            } else if (currentStatus == CurrentStatusEnum.CONFIRMED) {
                orderTracking.setConfirmedTime(time);
            } else if (currentStatus == CurrentStatusEnum.CANCELLED) {
                orderTracking.setCancelledTime(time);
            }

            if (orderTrackingService.updateById(orderTracking)
                    && super.updateById(order))
                return time;
            else
                throw new MyException("更新订单跟踪信息失败");
        } else {
            throw new MyException("更新订单状态失败");
        }
    }

    @Override
    public boolean updateReviewId(Long orderId, Long reviewId) {
        if (orderId == null || reviewId == null)
            throw new MyException("订单信息不完整");

        return super.update(
                new LambdaUpdateWrapper<Order>()
                        .eq(Order::getId, orderId)
                        .set(Order::getReviewId, reviewId)
        );
    }

    @Override
    public boolean removeReviewId(Long orderId) {
        if (orderId == null)
            throw new MyException("订单信息不完整");

        return super.update(
                new LambdaUpdateWrapper<Order>()
                        .eq(Order::getId, orderId)
                        .set(Order::getReviewId, null)
        );
    }

    @Override
    public boolean updatePaymentId(Long orderId, Long paymentId) {
        if (orderId == null || paymentId == null)
            throw new MyException("订单信息不完整");

        return super.update(
                new LambdaUpdateWrapper<Order>()
                        .eq(Order::getId, orderId)
                        .set(Order::getPaymentId, paymentId)
        );
    }

    @Override
    public boolean updateOrderTrackingId(Long orderId, Long orderTrackingId) {
        if (orderId == null || orderTrackingId == null)
            throw new MyException("订单信息不完整");

        return super.update(
                new LambdaUpdateWrapper<Order>()
                        .eq(Order::getId, orderId)
                        .set(Order::getOrderTrackingId, orderTrackingId)
        );
    }

    @Override
    public long countByClientId(Long clientId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
        );
    }

    @Override
    public long countByCourierId(Long courier) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCourierId, courier)
        );
    }

    @Override
    public long countTakeoutByClientId(Long clientId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getServiceType, ServiceTypeEnum.TAKEOUT)
        );
    }

    @Override
    public long countExpressByClientId(Long clientId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getServiceType, ServiceTypeEnum.EXPRESS)
        );
    }

    @Override
    public long countFlashByClientId(Long clientId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .eq(Order::getServiceType, ServiceTypeEnum.FLASH)
        );
    }

    @Override
    public long countUnconfirmedByClientId(Long clientId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getClientId, clientId)
                        .and(orderLambdaQueryWrapper -> orderLambdaQueryWrapper
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.ACCEPTED)
                                .or()
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.TRANSITING)
                                .or()
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.OVERDUE)
                                .or()
                                .eq(Order::getCurrentStatus, CurrentStatusEnum.DELIVERED)
                        )
        );
    }

    @Override
    public List<Order> pageUntransittedByCourierId(Long courierId, long current, long size) {
        List<Order> orders = new ArrayList<>();
        orderTrackingService.untransitted()
                .forEach(orderTracking -> {
                    Order order = super.getById(orderTracking.getOrderId());
                    if (order.getCourierId().equals(courierId))
                        orders.add(order);
                });
        List<Order> filtered = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            long index = (current - 1) * size + i;
            if (index < orders.size())
                filtered.add(orders.get((int) index));
        }
        return filtered;
    }

    @Override
    public long countUntransittedByCourierId(Long courierId) {
        List<Order> orders = new ArrayList<>();
        orderTrackingService.untransitted()
                .forEach(orderTracking -> {
                    Order order = super.getById(orderTracking.getOrderId());
                    if (order.getCourierId().equals(courierId))
                        orders.add(order);
                });
        return orders.size();
    }

    @Override
    public List<Order> pageTransittingByCourierId(Long courierId, long current, long size) {
        List<Order> orders = new ArrayList<>();
        orderTrackingService.transitting()
                .forEach(orderTracking -> {
                    Order order = super.getById(orderTracking.getOrderId());
                    if (order.getCourierId().equals(courierId))
                        orders.add(order);
                });
        List<Order> filtered = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            long index = (current - 1) * size + i;
            if (index < orders.size())
                filtered.add(orders.get((int) index));
            else
                break;
        }
        return filtered;
    }

    @Override
    public long countTransittingByCourierId(Long courierId) {
        List<Order> orders = new ArrayList<>();
        orderTrackingService.transitting()
                .forEach(orderTracking -> {
                    Order order = super.getById(orderTracking.getOrderId());
                    if (order.getCourierId().equals(courierId))
                        orders.add(order);
                });
        return orders.size();
    }

    @Override
    public List<Order> pageArrivedByCourierId(Long courierId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCourierId, courierId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.DELIVERED)
        ).getRecords();
    }

    @Override
    public long countArrivedByCourierId(Long courierId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCourierId, courierId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.DELIVERED)
        );
    }

    @Override
    public List<Order> pageConfirmedByCourierId(Long courierId, long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCourierId, courierId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.CONFIRMED)
        ).getRecords();
    }

    @Override
    public long countConfirmedByCourierId(Long courierId) {
        return super.count(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getCourierId, courierId)
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.CONFIRMED)
        );
    }

    @Override
    public void checkStatus() {
        orderMapper.checkStatus();
    }
}