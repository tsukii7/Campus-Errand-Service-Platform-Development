package com.example.ordertaker.AOP.exceptionInfo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionEnumTest {

    @Test
    void testGetResultCode() {
        assertThat(ExceptionEnum.SUCCESS.getResultCode()).isEqualTo("2000");
        assertThat(ExceptionEnum.BODY_NOT_MATCH.getResultCode()).isEqualTo("4000");
        assertThat(ExceptionEnum.NULL_POINTER.getResultCode()).isEqualTo("4005");
        assertThat(ExceptionEnum.SIGNATURE_NOT_MATCH.getResultCode()).isEqualTo("4001");
        assertThat(ExceptionEnum.NOT_FOUND.getResultCode()).isEqualTo("4004");
        assertThat(ExceptionEnum.INTERNAL_SERVER_ERROR.getResultCode()).isEqualTo("5000");
        assertThat(ExceptionEnum.SERVER_BUSY.getResultCode()).isEqualTo("5003");
        assertThat(ExceptionEnum.DUPLICATE_KEY.getResultCode()).isEqualTo("4444");
        assertThat(ExceptionEnum.TOO_MANY_RESULTS.getResultCode()).isEqualTo("4444");
    }

    @Test
    void testGetResultMsg() {
        assertThat(ExceptionEnum.SUCCESS.getResultMsg()).isEqualTo("成功!");
        assertThat(ExceptionEnum.BODY_NOT_MATCH.getResultMsg()).isEqualTo("请求的数据格式不符!");
        assertThat(ExceptionEnum.NULL_POINTER.getResultMsg()).isEqualTo("空指针异常!");
        assertThat(ExceptionEnum.SIGNATURE_NOT_MATCH.getResultMsg()).isEqualTo("请求的数字签名不匹配!");
        assertThat(ExceptionEnum.NOT_FOUND.getResultMsg()).isEqualTo("未找到该资源!");
        assertThat(ExceptionEnum.INTERNAL_SERVER_ERROR.getResultMsg()).isEqualTo("服务器内部错误!");
        assertThat(ExceptionEnum.SERVER_BUSY.getResultMsg()).isEqualTo("服务器正忙，请稍后再试!");
        assertThat(ExceptionEnum.DUPLICATE_KEY.getResultMsg()).isEqualTo("违背重复主键约束!");
        assertThat(ExceptionEnum.TOO_MANY_RESULTS.getResultMsg()).isEqualTo("数据库返回结果过多!");
    }
}
