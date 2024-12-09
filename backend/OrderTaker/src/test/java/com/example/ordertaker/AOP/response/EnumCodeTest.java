package com.example.ordertaker.AOP.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnumCodeTest {

    @Test
    void testGetCode() {
        assertThat(EnumCode.SUCCESS.getCode()).isEqualTo(0);
        assertThat(EnumCode.SYSTEM_ERROR.getCode()).isEqualTo(5001);
        assertThat(EnumCode.PARAMETER_ERROR.getCode()).isEqualTo(5002);
        assertThat(EnumCode.USER_HAS_ERROR.getCode()).isEqualTo(5003);
    }

    @Test
    void testGetMessage() {
        assertThat(EnumCode.SUCCESS.getMessage()).isEqualTo("ok");
        assertThat(EnumCode.SYSTEM_ERROR.getMessage()).isEqualTo("服务器系统异常，请稍后...");
        assertThat(EnumCode.PARAMETER_ERROR.getMessage()).isEqualTo("参数异常，认证失败...");
        assertThat(EnumCode.USER_HAS_ERROR.getMessage()).isEqualTo("用户名已存在....");
    }
}
