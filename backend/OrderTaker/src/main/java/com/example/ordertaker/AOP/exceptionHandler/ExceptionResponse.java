package com.example.ordertaker.AOP.exceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.example.ordertaker.AOP.exceptionInfo.BaseErrorInfoInterface;
import com.example.ordertaker.AOP.exceptionInfo.ExceptionEnum;

/**
 * @description: 自定义数据传输
 * @author: DT
 * @date: 2021/4/19 21:47
 * @version: v1.0
 */
public class ExceptionResponse {
    /**
     * 响应代码
     */
    private String code = "-1";

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public ExceptionResponse() {
    }

    public ExceptionResponse(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ExceptionResponse success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ExceptionResponse success(Object data) {
        ExceptionResponse rb = new ExceptionResponse();
        rb.setCode(ExceptionEnum.SUCCESS.getResultCode());
        rb.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ExceptionResponse error(BaseErrorInfoInterface errorInfo) {
        if (errorInfo != null) {
            ExceptionResponse rb = new ExceptionResponse();
            rb.setCode(errorInfo.getResultCode());
            rb.setMessage(errorInfo.getResultMsg());
            rb.setResult(null);
            return rb;
        }
        return null;

    }

    /**
     * 失败
     */
    public static ExceptionResponse error(String code, String message) {
        ExceptionResponse rb = new ExceptionResponse();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ExceptionResponse error(String message) {
        ExceptionResponse rb = new ExceptionResponse();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}

