package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blacklist")
public class Blacklist {

    @Schema(description = "黑名单主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "骑手主键id")
    @TableField()
    private Long courierId;

    @Schema( description = "黑名单创建时间")
    @TableField()
    private Date createdTime;

    @Schema(description = "该记录是否已被删除")
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
