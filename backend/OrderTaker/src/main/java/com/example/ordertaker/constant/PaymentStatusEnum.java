package com.example.ordertaker.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
    PENDING_PAYMENT (0, "待支付"),
    PAID            (1, "已支付"),
    REFUNDED        (2, "已取消");

    @EnumValue
    private final int key;

    @JsonValue
    private final String display;

    PaymentStatusEnum(int key, String display) {
        this.key = key;
        this.display = display;
    }
}
