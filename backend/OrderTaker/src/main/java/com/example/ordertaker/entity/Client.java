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
@TableName("client")
@Data
public class Client {

    @Schema( description = "用户主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema( description = "用户名字")
    @TableField()
    private String clientName;

    @Schema( description = "用户头像")
    @TableField()
    private String avatarUrl;

    @Schema( description = "用户邮箱")
    @TableField()
    private String email;

    @Schema( description = "用户密码")
    @TableField()
    private String password;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


//    @TableField(exist = false)
//    List<DeliveryAddress> deliveryAddressList = new ArrayList<>();
//
//    @TableField(exist = false)
//    List<Order> orderList = new ArrayList<>();
//
//    @TableField(exist = false)
//    List<Message> messageList = new ArrayList<>();
//
//    @TableField(exist = false)
//    List<Review> reviewList = new ArrayList<>();


}
