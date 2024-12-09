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

@Schema(description = "评论")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("review")
public class Review {

    @Schema( description = "评论主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema( description = "订单主键id")
    @JsonIgnore
    @TableField()
     private Long orderId;

    @Schema( description = "评分")
    @TableField()
    private Double rating;

    @Schema( description = "评论")
    @TableField()
    private String comment;

    @Schema( description = "用户主键id")
    @JsonIgnore
    @TableField()
//    private Client client;
     private Long clientId;


    @Schema( description = "骑手主键id")
    @JsonIgnore
    @TableField()
//    private Courier courier;
     private Long courierId;

    @Schema( description = "评论创建时间")
    @TableField()
    private Date createdTime;


}