package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.constant.PaymentStatusEnum;
import com.example.ordertaker.entity.Payment;

public interface PaymentService extends IService<Payment> {
    @Override boolean save(Payment payment);

    boolean updateStatus(Long id, PaymentStatusEnum status);

    PaymentStatusEnum getStatus(Long id);
}
