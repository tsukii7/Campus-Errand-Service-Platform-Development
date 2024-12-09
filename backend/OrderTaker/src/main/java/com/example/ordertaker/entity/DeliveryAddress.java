package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("delivery_address")
public class DeliveryAddress {

    @Schema( description = "收货地址主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema( description = "用户主键id")
    @TableField()
    private Long clientId;

    @Schema( description = "收货地址")
    @TableField()
    private String address;

    @Schema( description = "收货人电话")
    @TableField()
    private String phoneNumber;

    @Schema( description = "收货人姓名")
    @TableField()
    private String receiver;

}
