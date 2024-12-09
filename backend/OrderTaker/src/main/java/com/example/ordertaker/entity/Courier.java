package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "List<Review> reviewList = new ArrayList<>();")
@AllArgsConstructor
@NoArgsConstructor
@TableName("courier")
@Data
public class Courier {

    @Schema(description = "骑手主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema( description = "骑手名字")
    @TableField()
    private String courierName;

    @Schema( description = "骑手头像")
    @TableField()
    private String avatarUrl;

    @Schema( description = "骑手邮箱")
    @TableField()
    private String email;

    @Schema( description = "骑手密码")
    @TableField()
    private String password;

    @Schema( description = "骑手评分")
    @TableField()
    private Double rating;

    @Schema( description = "骑手被封次数")
    @TableField()
    private Integer blockTimes;

//    @TableField(exist = false)
//    List<Order> orderList = new ArrayList<>();
//
//    @TableField(exist = false)
//    List<Message> messageList = new ArrayList<>();
//
//    @TableField(exist = false)
//    List<Review> reviewList = new ArrayList<>();

}
