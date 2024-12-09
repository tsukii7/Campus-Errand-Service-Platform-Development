package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.constant.PaymentStatusEnum;
import com.example.ordertaker.entity.Payment;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.PaymentMapper;
import com.example.ordertaker.service.OrderService;
import com.example.ordertaker.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
    @Autowired
    OrderService orderService;

    @Override
    public boolean save(Payment payment) {
        if (payment.getTransactionId() == null || payment.getTransactionId().isEmpty()
                || payment.getOrderId() == null)
            throw new MyException("支付信息不完整");

        if(payment.getStatus() == null)
            payment.setStatus(PaymentStatusEnum.PENDING_PAYMENT);
        if(payment.getCreatedTime() == null)
            payment.setCreatedTime(new Date());

        if (super.save(payment)) {
            orderService.updatePaymentId(payment.getOrderId(), payment.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatus(Long id,PaymentStatusEnum status) {
        if (id == null || status == null)
            throw new MyException("支付信息不完整");

        return super.update(
                new LambdaUpdateWrapper<Payment>()
                        .eq(Payment::getId, id)
                        .set(Payment::getStatus, status));
    }

    @Override
    public PaymentStatusEnum getStatus(Long id) {
        if (id == null)
            throw new MyException("支付信息不完整");

        Payment payment = super.getById(id);
        if (payment == null)
            throw new MyException("支付信息不存在");

        return payment.getStatus();
    }

}
