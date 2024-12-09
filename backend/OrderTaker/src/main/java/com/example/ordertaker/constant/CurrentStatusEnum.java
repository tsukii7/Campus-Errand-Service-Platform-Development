package com.example.ordertaker.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CurrentStatusEnum {

    PLACED      (0, "已下单"),
    ACCEPTED    (1, "已接单"),
    TRANSITING  (2, "配送中"),
    OVERDUE     (3, "已超时"),
    DELIVERED   (4, "已送达"),
    CONFIRMED   (5, "已确认"),
    CANCELLED   (6, "已取消");

    @EnumValue
    private final int key;

    @JsonValue
    private final String display;


    CurrentStatusEnum(int key, String display){
        this.key = key;
        this.display = display;
    }


}
