package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_tracking")
public class OrderTracking {

    @Schema( description = "订单追踪主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单主键id")
    @JsonIgnore
    @TableField()
    private Long orderId;

    @Schema(description = "订单创建时间")
    @TableField()
    private Date createdTime;

    @Schema( description = "物品到达时间")
    @TableField()
    private Date placedTime;

    @Schema( description = "物品取走时间")
    @TableField()
    private Date transitedTime;

    @Schema( description = "物品送达时间")
    @TableField()
    private Date arrivedTime;

    @Schema( description = "订单确认时间")
    @TableField()
    private Date confirmedTime;

    @Schema( description = "订单取消时间")
    @TableField()
    private Date cancelledTime;

}
