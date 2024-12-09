package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.ordertaker.constant.PaymentStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "}")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("payment")
public class Payment {
    @Schema( description = "支付主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 交易ID，支付平台返回的唯一标识
    @Schema(description = "交易ID，支付平台返回的唯一标识")
    @TableField()
    private String transactionId;

    @Schema( description = "订单主键id")
    @TableField
    private Long orderId;


    @Schema( description = "支付状态")
    @TableField()
    private PaymentStatusEnum status;

    @Schema( description = "支付创建时间")
    @TableField()
    private Date createdTime;

    @Schema( description = "支付更新时间")
    @TableField()
    private Date updatedTime;

//    public void setStatus(PaymentStatusEnum status) {
//        this.status = status;
//    }

}