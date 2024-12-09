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
@TableName("message")
public class Message {

    @Schema( description = "消息主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema( description = "用户主键id")
    @JsonIgnore
    @TableField()
     private Long clientId;

    @Schema (description = "骑手主键id")
    @JsonIgnore
    @TableField()
     private Long courierId;

    @Schema( description = "消息内容")
    @TableField()
    private String content;

    @Schema( description = "消息发送者id")
    @TableField()
    private Long senderId;

    @Schema( description = "发送时间")
    @TableField()
    private Date time;
}
