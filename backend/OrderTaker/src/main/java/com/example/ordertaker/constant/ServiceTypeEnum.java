package com.example.ordertaker.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ServiceTypeEnum {
    TAKEOUT (0, "外卖"),
    EXPRESS (1, "快递"),
    FLASH   (2, "闪送");

    @EnumValue
    private final int key;

    @JsonValue
    private final String display;

    ServiceTypeEnum(int key, String display) {
        this.key = key;
        this.display = display;
    }
}
