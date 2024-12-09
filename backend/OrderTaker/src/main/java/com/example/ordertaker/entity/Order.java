package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.ordertaker.constant.CurrentStatusEnum;
import com.example.ordertaker.constant.ServiceTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "}")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("`order`")
public class Order {
    // ==============共有属性================

    @Schema( description = "订单主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema( description = "用户主键id")
    @TableField()
    private Long clientId;

    @Schema( description = "骑手主键id")
    @TableField()
    private Long courierId;

    @Schema( description = "支付主键id")
    @TableField()
    private Long paymentId;

    @Schema(description = "取货地址")
    @TableField()
    private String pickUpAddress;

    @Schema( description = "送货地址主键id")
    @TableField()
    private Long deliveryAddressId;

    @Schema( description = "订单跟踪主键id")
    @TableField()
    private Long orderTrackingId;


    @Schema( description = "订单状态")
    @TableField()
    private CurrentStatusEnum currentStatus;

    @Schema( description = "评论主键id")
    @TableField()
    private Long reviewId;

    @Schema( description = "订单类型")
    @TableField()
    private ServiceTypeEnum serviceType;

    @Schema( description = "骑手完成送货时间")
    @TableField()
    private Date expectedArrivedTime;

    @Schema( description = "跑腿费用")
    @TableField()
    private Integer fee;

    @Schema( description = "订单描述")
    @TableField()
    private String description;


    //================TakeoutOrder特有属性================

    @Schema(hidden = true)
    @TableField()
    private String restaurant;

    // 外卖预计到达校门口的时间
    @Schema(description = "外卖预计到达校门口的时间")
    @TableField()
    private Date estimatedArrivedTime;

    @Schema(hidden = true)
    @TableField()
    private Integer totalPrice;


    //================ExpressOrder特有属性================

    @Schema(hidden = true)
    @TableField()
    private String pickUpCode;

    @Schema(hidden = true)
    @TableField()
    private String size;

    @Schema(hidden = true)
    @TableField()
    private Double weight;


    //================FlashOrder没有特有属性================

    //    public void setServiceType(int i) {
//        this.serviceType = ServiceTypeEnum.values()[i];
//    }
//
//    public void setServiceType(ServiceTypeEnum serviceType) {
//        this.serviceType = serviceType;
//    }
//
//    public void setCurrentStatus(int i) {
//        this.currentStatus = CurrentStatusEnum.values()[i];
//    }
//
//    public void setCurrentStatus(CurrentStatusEnum currentStatus) {
//        this.currentStatus = currentStatus;
//    }
}
