package com.example.ordertaker.AOP.exceptionHandler;

import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.AOP.exceptionInfo.ExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * @description: 自定义异常处理
 * @author: DT
 * @date: 2021/4/19 21:51
 * @version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value =Exception.class)
//    @ResponseBody
//    public String exceptionHandler(Exception e){
//        System.out.println("全局异常捕获>>>:"+e);
//        return "全局异常捕获,错误原因>>>"+e.getMessage();
//    }
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ExceptionResponse MyExceptionHandler(HttpServletRequest req, MyException e){
            logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ExceptionResponse.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ExceptionResponse exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是：{}",e.getMessage());
        return ExceptionResponse.error(ExceptionEnum.NULL_POINTER);
    }
    
    /**
     *
     * 处理报文数据段的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = TooManyResultsException.class)
    @ResponseBody
    public ExceptionResponse exceptionHandler(HttpServletRequest req, TooManyResultsException e){
        logger.error("数据库查询结果异常！原因是：{}",e.getMessage());
        return ExceptionResponse.error(ExceptionEnum.DUPLICATE_KEY);
    }

    /**
     * 处理报文数据段的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ExceptionResponse exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e){
        logger.error("请求报文异常！原因是：{}",e.getMessage());
        return ExceptionResponse.error(ExceptionEnum.BODY_NOT_MATCH);
    }


    /**
     * 处理重复主键的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public ExceptionResponse exceptionHandler(HttpServletRequest req, SQLIntegrityConstraintViolationException e){
        logger.error("发生数据库约束异常！原因是：{}",e.getMessage());
        return ExceptionResponse.error(ExceptionEnum.DUPLICATE_KEY);
    }

//    /**
//     * 处理其他异常
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value =Exception.class)
//    @ResponseBody
//    public ExceptionResponse exceptionHandler(HttpServletRequest req, Exception e){
//        logger.error("未知异常！原因是:{}",e.getMessage());
//        System.out.println("未知异常！原因是:"+e.getMessage());
//        return ExceptionResponse.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
//    }
}
